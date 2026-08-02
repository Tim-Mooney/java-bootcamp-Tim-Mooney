package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;  failure test
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

//import static org.junit.jupiter.api.Assertions.assertEquals; failure test
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerNotifierMockTest {

    @Mock
    private CustomerNotifier notifier;

//    @Test
//    void addCustomerInvokesNotifierWithCreatedCustomer() {
//        CustomerService service = new CustomerService(notifier);
//        Customer amina = new Customer("CUS-1001", "Amina Khan", "amina@example.com",
//                "555-1234", CustomerStatus.ACTIVE, LocalDateTime.now());
//
//        service.addCustomer(amina);
//
//        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
//        verify(notifier).notifyCreated(captor.capture());
//
//        Customer captured = captor.getValue();
//        assertEquals("CUS-1001", captured.getCustomerId());
//        assertEquals("Amina Khan", captured.getFullName());
//    }

    @Test
    void updateStatusInvokesNotifierWithOldAndNewStatus() {
        CustomerService service = new CustomerService(notifier);
        Customer ravi = new Customer("CUS-1002", "Ravi Singh", "ravi.singh@example.com",
                "555-0102", CustomerStatus.PROSPECT, LocalDateTime.now());
        service.addCustomer(ravi);

        service.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        verify(notifier).notifyStatusChange("CUS-1002", CustomerStatus.PROSPECT, CustomerStatus.ACTIVE);
    }
}
