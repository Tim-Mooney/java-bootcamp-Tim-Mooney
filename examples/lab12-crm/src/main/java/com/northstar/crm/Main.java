package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 12 — refactor CustomerService (doStuff -> clean API)");

        CustomerService service = new CustomerService();

        try {
            service.createCustomer("", "Duplicate", "dup@example.com",
                    "555-0000", CustomerStatus.ACTIVE);
        } catch (IllegalArgumentException e) {
            System.out.println("Empty ID -> IllegalArgumentException: " + e.getMessage());
        }


        // create CUS-1001 Amina Khan ACTIVE
        Customer amina = service.createCustomer("CUS-1001", "Amina Khan",
                "amina@example.com", "555-1234", CustomerStatus.ACTIVE);
        System.out.println("create CUS-1001 -> " + amina);

        // create CUS-1002 Ravi Singh PROSPECT
        Customer ravi = service.createCustomer("CUS-1002", "Ravi Singh",
                "ravi.singh@example.com", "555-0102", CustomerStatus.PROSPECT);
        System.out.println("create CUS-1002 -> " + ravi);

        // get CUS-1001 -> Amina Khan
        Customer fetched = service.getCustomer("CUS-1001");
        System.out.println("get CUS-1001 -> " + fetched.getFullName());

        // updateStatus CUS-1002 ACTIVE
        Customer updated = service.updateStatus("CUS-1002", CustomerStatus.ACTIVE);
        System.out.println("updateStatus CUS-1002 -> " + updated.getStatus());

        // duplicate CUS-1001 -> IllegalStateException
        try {
            service.createCustomer("CUS-1001", "Duplicate", "dup@example.com",
                    "555-0000", CustomerStatus.ACTIVE);
        } catch (IllegalStateException e) {
            System.out.println("duplicate CUS-1001 -> IllegalStateException: " + e.getMessage());
        }

        // unknown CUS-9999 -> IllegalArgumentException (+ correlationId)
        try {
            service.getCustomer("CUS-9999");
        } catch (IllegalArgumentException e) {
            System.out.println("unknown CUS-9999 -> IllegalArgumentException: " + e.getMessage());
        }
    }
}