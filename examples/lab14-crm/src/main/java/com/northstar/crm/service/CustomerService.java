package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Lab 12-shaped clean API baseline for Lab 14 DTO boundary. */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer createCustomer(String customerId, String fullName, String email,
                                   String phone, CustomerStatus status) {
        if (customerId == null || customerId.isBlank() || fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("customerId and fullName are required");
        }
        if (findByCustomerId(customerId).isPresent()) {
            throw new IllegalStateException("Duplicate customerId: " + customerId);
        }
        Customer c = new Customer(customerId, fullName, email, phone,
                status != null ? status : CustomerStatus.PROSPECT, LocalDateTime.now());
        customers.add(c);
        return c;
    }

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalStateException("Duplicate customerId: " + customer.getCustomerId());
        }
        customers.add(customer);
        return customer;

    }

    public Customer getCustomer(String customerId) {
        return findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst();
    }
}
