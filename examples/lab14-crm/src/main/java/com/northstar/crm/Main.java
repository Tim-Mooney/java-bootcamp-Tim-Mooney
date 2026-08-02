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

        CustomerRequestDTO cus1002 = new CustomerRequestDTO(
                "CUS-1002", "Ravi Singh", "ravi.singh@example.com", "PROSPECT");
        CustomerResponseDTO created1002 = api.create(cus1002, CORRELATION_ID);
        printResponse("Created", created1002);


        // TODO: create CUS-1001 / CUS-1002 via DTOs; print CustomerResponseDTO only
        // TODO: attempt invalid email; show correlation lab-request-001 in failure
        //throw new UnsupportedOperationException("TODO: DTO facade demo");
    }

    private static void printResponse(String label, CustomerResponseDTO dto) {
        System.out.println(label + ": " + dto.getCustomerId() + " | " + dto.getFullName()
                + " | " + dto.getEmail() + " | " + dto.getStatus() + " | " + dto.getCreatedAt());
    }
}
