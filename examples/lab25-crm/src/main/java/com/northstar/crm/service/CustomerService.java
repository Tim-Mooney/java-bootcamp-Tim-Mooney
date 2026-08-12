package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer create(Customer customer, String correlationId) {
    Optional<Customer> result = customerRepository.findById(customer.getId());
    if (result.isPresent()) {
      throw new IllegalStateException("Duplicate customer: " + customer.getId());
    }
    customerRepository.save(customer);
    log.info("Saved customer "+ customer.getId() + " correlation id "+ correlationId);
    return customer;
  }

  public Customer get(String id) {
    Optional<Customer> result = customerRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new IllegalArgumentException("Customer not found: " + id);
    }
  }

  public List<Customer> list() {
    return customerRepository.findAll();
  }
}
