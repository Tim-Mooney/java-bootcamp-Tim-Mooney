package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer input, String correlationId) {
        long start = System.nanoTime();
        MDC.put("customerId", input.getCustomerId());
        MDC.put("op", "customer.create");
        log.info("create customer id={}", input.getCustomerId());
        if (input.getCustomerId() == null || input.getCustomerId().isBlank()) {
            log.warn("reject create reason=missing_customer_id");
            throw new IllegalArgumentException("customerId required [" + correlationId + "]");
        }
        Customer createdCust = repository.save(input);
        long durationMs = (System.nanoTime() - start) / 1_000_000;
        log.info("create complete durationMs={}", durationMs);
        return createdCust;
    }

    public Optional<Customer> findById(String id) {
        long start = System.nanoTime();
        MDC.put("customerId", id);
        MDC.put("op", "customer.get");
        try {
            log.info("Loading customer");
            Optional<Customer> foundCust = repository.findById(id);
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            log.info("findById complete durationMs={}", durationMs);
            return foundCust;
        } finally {
            MDC.remove("customerId");
            MDC.remove("op");
        }
    }
}
