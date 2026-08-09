package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.northstar.crm.repository.CustomerRepository;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final NotificationService notificationService;
  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);


  public CustomerService(CustomerRepository customerRepository, NotificationService notificationService) {
    this.customerRepository = customerRepository;
    this.notificationService = notificationService;
  }

  public Customer create(Customer customer, String correlationId) {
    Customer saved = customerRepository.save(customer);
    notificationService.notifyCreated(saved.getId(), correlationId);
    return saved;
  }

  public Customer get(String id) {
    return customerRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
  }

  @PostConstruct
  void init() {
    log.info("CustomerService ready");
  }

  @PreDestroy
  void shutdown() {
    log.info("CustomerService shutting down");
  }
}
