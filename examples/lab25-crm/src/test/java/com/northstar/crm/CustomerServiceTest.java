package com.northstar.crm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;


class CustomerServiceTest {

  @Test
  void getSeededAmina() {
    CustomerService service = new CustomerService(new InMemoryCustomerRepository());

    Customer customer = service.get("CUS-1001");
    assertEquals("Amina Khan", customer.getName());
  }

  @Test
  void duplicateCreateRejected() {
    CustomerService service = new CustomerService(new InMemoryCustomerRepository());
    assertThrows(IllegalStateException.class, () -> {
      service.create(Customer.amina(), "lab-request-001");
    });
  }

}
