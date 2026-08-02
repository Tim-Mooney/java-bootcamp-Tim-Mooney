package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTests {
    DefaultCustomerService service;
    CustomerRepository repository;
    CustomerValidator validator;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCustomerRepository();
        validator = new CustomerValidator(repository);
        service = new DefaultCustomerService(repository, validator);
    }

    @Test
    void addAndActivateRaviHappyPath() {
        Customer amina = service.addCustomer(Customer.amina());
        assertEquals("CUS-1001", amina.getCustomerId());
        assertEquals(CustomerStatus.ACTIVE, amina.getStatus());

        Customer ravi = service.addCustomer(Customer.ravi());
        assertEquals("CUS-1002", ravi.getCustomerId());
        assertEquals(CustomerStatus.PROSPECT, ravi.getStatus());

        Customer activated = service.changeStatus(
                "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        assertEquals("CUS-1002", activated.getCustomerId());
        assertEquals(CustomerStatus.ACTIVE, activated.getStatus());
    }

    @Test
    void duplicateIdThrowsConflict() {
        service.addCustomer(Customer.amina());

        BusinessException ex = assertThrows(BusinessException.class,
                () -> service.addCustomer(Customer.amina()));

        assertEquals("BUSINESS_CONFLICT", ex.getCode());
        assertEquals(409, ex.getStatusHint());
    }

    @Test
    void illegalTransitionThrowsConflict() {
        Customer amina = service.addCustomer(Customer.amina());

        BusinessException ex = assertThrows(BusinessException.class,
                () -> service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001"));

        assertEquals("BUSINESS_CONFLICT", ex.getCode());
        assertEquals(409, ex.getStatusHint());
        assertEquals(CustomerStatus.ACTIVE, amina.getStatus());

    }
//    @Test
//    void illegalTransitionSucceeds() {
//        Customer amina = service.addCustomer(Customer.amina());
//
//        BusinessException ex = assertThrows(BusinessException.class,
//                () -> service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001"));
//
//        assertEquals(CustomerStatus.PROSPECT, amina.getStatus());
//
//    }

    @Test
    void missingCustomerThrowsNotFound() {
        BusinessException ex = assertThrows(BusinessException.class,
                () -> service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, "lab-request-001"));

        assertEquals("CUSTOMER_NOT_FOUND", ex.getCode());
        assertEquals(404, ex.getStatusHint());
    }

    @Test
    void emptyUserId() {
        Customer noId = new Customer("", "Someone Person", "someone@example.com", null,
                CustomerStatus.PROSPECT, LocalDateTime.now());

        assertThrows(BusinessException.class,
                () -> service.addCustomer(noId));
    }
//AI TESTS BELOW
    @Test
    void duplicateEmailThrowsConflict() {
        service.addCustomer(Customer.amina());
        // create different id but same email as amina
        Customer other = new Customer("CUS-2001", "Other Person", "amina.khan@example.com", null,
                CustomerStatus.PROSPECT, LocalDateTime.now());

        BusinessException ex = assertThrows(BusinessException.class,
                () -> service.addCustomer(other));

        assertEquals("BUSINESS_CONFLICT", ex.getCode());
        assertEquals(409, ex.getStatusHint());
        assertTrue(ex.getMessage().contains("duplicate email"));
    }

    @Test
    void listAllReturnsAllCustomers() {
        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());

        var all = service.listAll();
        assertEquals(2, all.size());
        assertTrue(all.stream().anyMatch(c -> "CUS-1001".equals(c.getCustomerId())));
        assertTrue(all.stream().anyMatch(c -> "CUS-1002".equals(c.getCustomerId())));
    }

    @Test
    void businessExceptionContainsCorrelationId() {
        String cid = "lad-request-001";
        BusinessException ex = assertThrows(BusinessException.class,
                () -> service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, cid));

        assertEquals(cid, ex.getCorrelationId());
    }
}
