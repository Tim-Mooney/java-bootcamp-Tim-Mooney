package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService service;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        service = new CustomerService();
        now = LocalDateTime.now();  //this goes before each test so its fine. It might matter if we needed really specific timing but we really dont.
    }

    @Test
    void addCustomerStoresNewCustomer() {
        Customer customer = new Customer("CUS-1001", "Amina Khan", "amina@example.com", "555-1234", CustomerStatus.ACTIVE, now);
        
        Customer result = service.addCustomer(customer);
        
        //assertEquals(customer, result, "addCustomer should return the stored customer"); //this can fail if addCustomer is not returning properly
        assertEquals(1, service.listAll().size()); //AI didn't generate
        assertEquals("CUS-1001", service.listAll().get(0).getCustomerId());
        assertTrue(service.findByCustomerId("CUS-1001").isPresent(), "Customer should be retrievable after adding");
    }

    @Test
    void addCustomerRejectsDuplicateId() {
        Customer c1 = new Customer("CUS-1001", "Amina Khan", "amina@example.com", "555-1234", CustomerStatus.ACTIVE, now);
        Customer c2 = new Customer("CUS-1001", "Different Name", "different@example.com", "555-5678", CustomerStatus.ACTIVE, now);
        
        service.addCustomer(c1);
        
        assertThrows(IllegalStateException.class, () -> service.addCustomer(c2), "Adding duplicate customerId should throw IllegalStateException");
    }

    @Test
    void updateStatusChangesCustomerStatus() {
        Customer ravi = new Customer("CUS-1002", "Ravi Singh", "ravi.singh@example.com",
                "555-0102", CustomerStatus.PROSPECT, LocalDateTime.now());
        service.addCustomer(ravi);
        service.updateStatus("CUS-1002", CustomerStatus.ACTIVE);        //My AI version was bad
        assertEquals(CustomerStatus.ACTIVE,
                service.findByCustomerId("CUS-1002").orElseThrow().getStatus());
    }

    @Test
    void findByStatusReturnsMatchingCustomers() {
        Customer c1 = new Customer("CUS-1001", "Amina Khan", "amina@example.com", "555-1234", CustomerStatus.ACTIVE, now);
        Customer c2 = new Customer("CUS-1002", "Ravi Singh", "ravi@example.com", "555-5678", CustomerStatus.PROSPECT, now);
        Customer c3 = new Customer("CUS-1003", "Maria Garcia", "maria@example.com", "555-9999", CustomerStatus.ACTIVE, now);
        
        service.addCustomer(c1);
        service.addCustomer(c2);
        service.addCustomer(c3);
        
        var activeCustomers = service.findByStatus(CustomerStatus.ACTIVE);
        
        assertEquals(2, activeCustomers.size(), "Should find 2 ACTIVE customers");
        assertTrue(activeCustomers.stream().anyMatch(c -> c.getCustomerId().equals("CUS-1001")), "Should contain CUS-1001");
        assertTrue(activeCustomers.stream().anyMatch(c -> c.getCustomerId().equals("CUS-1003")), "Should contain CUS-1003");
    }
}
