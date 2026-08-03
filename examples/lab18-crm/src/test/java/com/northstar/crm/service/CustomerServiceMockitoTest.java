package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CustomerServiceMockitoTest {

    @Mock
    CustomerRepository repository;

    CustomerValidator validator;
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        validator = new CustomerValidator(repository);
        service = new DefaultCustomerService(repository, validator);
    }

    @Test
    void activatesProspectUsingStubbedRepository() {
        Customer ravi = Customer.ravi();
        //when(repository.findById("CUS-1002")).thenThrow(new RuntimeException("failure experiment 1"));

        when(repository.findById("CUS-1002")).thenReturn(Optional.of(ravi));
        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));
        //when(repository.findAll()).thenReturn(new ArrayList<>());

        Customer result = service.changeStatus(
                "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");

        assertEquals(CustomerStatus.ACTIVE, result.getStatus());
        verify(repository).findById("CUS-1002");
        verify(repository).save(argThat(c ->
                "CUS-1002".equals(c.getCustomerId()) && c.getStatus() == CustomerStatus.ACTIVE));
    }

    @Test
    void unknownCustomerDoesNotSave() {
        when(repository.findById("CUS-9999")).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () ->
                service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, "lab-request-001"));

        verify(repository).findById("CUS-9999");
        verify(repository, never()).save(any());
    }

    @Test
    void addCustomerCapturesSavedEntity() {
        when(repository.existsById("CUS-1001")).thenReturn(false);
        when(repository.existsByEmail("amina.khan@example.com")).thenReturn(false);
        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        service.addCustomer(Customer.amina());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(captor.capture());
        assertEquals("CUS-1001", captor.getValue().getCustomerId());
        assertEquals("Amina Khan", captor.getValue().getFullName());
        assertEquals(CustomerStatus.ACTIVE, captor.getValue().getStatus());
    }

    @Test
    void addCustomerWithDuplicateEmailThrowsAndNeverSaves() {
        when(repository.existsById("CUS-1001")).thenReturn(false);
        when(repository.existsByEmail("amina.khan@example.com")).thenReturn(true);

        assertThrows(BusinessException.class, () ->
                service.addCustomer(Customer.amina()));

        verify(repository).existsById("CUS-1001");
        verify(repository).existsByEmail("amina.khan@example.com");
        verify(repository, never()).save(any());
    }

    @Test
    void activeToProspectIsIllegalTransitionAndNeverSaves() {
        Customer amina = Customer.amina();

        when(repository.findById("CUS-1001")).thenReturn(Optional.of(amina));

        assertThrows(BusinessException.class, () ->
                service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001"));

        verify(repository).findById("CUS-1001");
        verify(repository, never()).save(any());
    }

//    @Test
//    void demonstratesVerificationFailureWithoutReset() {
//        Customer ravi = Customer.ravi();
//        when(repository.findById("CUS-1002")).thenReturn(Optional.of(ravi));
//        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        service.findById("CUS-1002");
//        service.findById("CUS-1002");
//
//        verify(repository, times(1)).save(any(Customer.class));
//    }
}
