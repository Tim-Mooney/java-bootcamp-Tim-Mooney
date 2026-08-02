package com.northstar.crm;

import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.service.CustomerService;

public class Main {
    private static final String CORRELATION_ID = "lab-request-001"; //my code does not smell
    public static void main(String[] args) {
        CustomerApiFacade api = new CustomerApiFacade(new CustomerService());
        CustomerRequestDTO cus1001 = new CustomerRequestDTO(
                "CUS-1001", "Amina Khan", "amina.khan@example.com", "ACTIVE");
        CustomerResponseDTO created1001 = api.create(cus1001, CORRELATION_ID);
        printResponse("Created", created1001);
        CustomerRequestDTO cus10012 = new CustomerRequestDTO(
                "CUS-1001", "Amina Khan", "amina.khan@example.com", "ACTIVE");
        try {
            CustomerResponseDTO created10012 = api.create(cus10012, CORRELATION_ID);
            printResponse("Created", created10012);
        } catch (IllegalStateException e) {
            boolean hasCorrelationId = e.getMessage().contains(CORRELATION_ID);
            System.out.println("Validation failure: " + e.getMessage());
            System.out.println("Contains correlation id [" + CORRELATION_ID + "]: " + hasCorrelationId);
        }

        CustomerRequestDTO cus1002 = new CustomerRequestDTO(
                "CUS-1002", "Ravi Singh", "ravi.singh@example.com", "PROSPECT");
        CustomerResponseDTO created1002 = api.create(cus1002, CORRELATION_ID);
        printResponse("Created", created1002);

        CustomerRequestDTO badEmail = new CustomerRequestDTO(
                "CUS-1003", "Ravi Singh", "nffes", "PROSPECT");
        try {
            CustomerResponseDTO created1003 = api.create(badEmail, CORRELATION_ID);
            printResponse("Created", created1003); // won't reach here — email is invalid
        } catch (IllegalArgumentException e) {
            boolean hasCorrelationId = e.getMessage().contains(CORRELATION_ID);
            System.out.println("Validation failure: " + e.getMessage());
            System.out.println("Contains correlation id [" + CORRELATION_ID + "]: " + hasCorrelationId);
        }

    }

    private static void printResponse(String label, CustomerResponseDTO dto) {
        System.out.println(label + ": " + dto.getCustomerId() + " | " + dto.getFullName()
                + " | " + dto.getEmail() + " | " + dto.getStatus() + " | " + dto.getCreatedAt());
    }
}
