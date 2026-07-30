package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void equalsUsesCustomerIdOnly() {
        LocalDateTime now = LocalDateTime.now();
        
        Customer c1 = new Customer("CUS-1001", "Alice", "alice@example.com", "555-1111", CustomerStatus.ACTIVE, now);
        Customer c2 = new Customer("CUS-1001", "Bob", "bob@example.com", "555-2222", CustomerStatus.CLOSED, now);   //it did CustomerStatus.INACTIVE which is not real
        
        assertEquals(c1, c2, "Customers with same customerId must be equal");
    }

    @Test
    void toStringContainsCustomerId() {
        LocalDateTime now = LocalDateTime.now();
        Customer customer = new Customer("CUS-1001", "Amina Khan", "amina@example.com", "555-1234", CustomerStatus.ACTIVE, now);
        
        String result = customer.toString();
        assertTrue(result.contains("CUS-1001"), "toString() must contain customerId");
    }
}
