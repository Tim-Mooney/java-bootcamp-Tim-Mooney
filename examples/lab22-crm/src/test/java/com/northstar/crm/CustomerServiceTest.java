package com.northstar.crm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.model.Customer;
import com.northstar.crm.service.NotificationService;
import com.northstar.crm.service.CustomerService;



class CustomerServiceTest {
  @Test
  void createAndGetWithoutSpringContext() {
    var repo = new InMemoryCustomerRepository();
    var notify = new NotificationService();
    var service = new CustomerService(repo, notify);

    Customer created = service.create(Customer.amina(), "lab-request-001");
    assertEquals("CUS-1001", created.getId());

    Customer found = service.get("CUS-1001");
    assertNotNull(found);
    assertEquals("Amina Khan", found.getName());
    assertEquals("ACTIVE", found.getStatus());
  }
}
