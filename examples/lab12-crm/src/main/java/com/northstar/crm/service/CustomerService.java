package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CustomerService {
    private final Map<String, Customer> customersById = new HashMap<>();

    public Customer createCustomer(String customerId, String fullName, String email, String phone, CustomerStatus status) {
        requireNonBlank(customerId, "Customer ID");
        requireNonBlank(fullName, "Full Name");
        requireNonBlank(email, "Email");
        requireNonBlank(phone, "Phone");

        if (!isUniqueId(customerId)) {
            throw new IllegalArgumentException("Customer ID must be unique: " + customerId);
        }

        Customer customer = new Customer(customerId, fullName, email, phone, status);
        customersById.put(customerId, customer);
        return customer;
    }

    public Customer getCustomer(String customerId) {
        Customer found = customersById.get(customerId);
        if (found == null) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId + " correlationId=" + correlationId());
        }
        return found;
    }

    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        Customer customer = requireExisting(customerId);
        CustomerStatus oldStatus = customer.getStatus();
        customer.setStatus(newStatus);
        System.out.println("Status changed for customer " + customerId + ": " + oldStatus + " -> " + newStatus);
        return customer;
    }

    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
    private boolean isUniqueId(String customerId) {
        return !customersById.containsKey(customerId);
    }
    private Customer requireExisting(String customerId) {
        Customer customer = customersById.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + customerId);
        }
        return customer;
    }

    // TODO: replace doStuff/get with createCustomer / getCustomer / updateStatus
    // TODO: typed List<Customer>, proper exceptions, equals (not ==), correlation-aware logs
}
