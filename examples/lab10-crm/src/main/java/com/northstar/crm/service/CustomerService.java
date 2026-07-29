package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        if (customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("customerId cannot be blank");
        }
        if (customers.stream().anyMatch(c -> c.getCustomerId().equals(customer.getCustomerId()))) {
            throw new IllegalStateException("Customer with ID " + customer.getCustomerId() + " already exists");
        }
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    public List<Customer> findByStatus(CustomerStatus status) {
        return customers.stream()
                .filter(c -> c.getStatus() == status)
                .toList();
    }

    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        Customer customer = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        customer.setStatus(newStatus);
        return customer;
    }

//    public List<Customer> listAll() {
//        return Collections.unmodifiableList(new ArrayList<>(customers));
//    }
    public List<Customer> listAll() {       //I think the ai code above is good but I can't say for sure so
        return List.copyOf(customers);
    }
}
