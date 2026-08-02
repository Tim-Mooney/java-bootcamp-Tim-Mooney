package com.northstar.crm;

import com.northstar.crm.api.ApiResult;
import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);
        CustomerApiFacade api = new CustomerApiFacade(service);

        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());

        String correlationId = "lab-request-001";

        System.out.println("=== Test 400 (Invalid Email) ===");
        CustomerRequestDTO badEmail = new CustomerRequestDTO("CUS-2001", "Test User", "bad-email", "ACTIVE");
        ApiResult result400 = api.create(badEmail, correlationId);
        printResult(result400);

        // 404: Customer not found
        System.out.println("\n=== Test 404 (Customer Not Found) ===");
        ApiResult result404 = api.getById("CUS-9999", correlationId);
        printResult(result404);

        // 409: Invalid status transition (attempt to change from ACTIVE to PROSPECT on non-existent customer)
        System.out.println("\n=== Test 409 (Conflict - Invalid Transition) ===");
        ApiResult result409 = api.changeStatus("CUS-1001", CustomerStatus.PROSPECT, correlationId);
        printResult(result409);

        System.out.println("\n=== Test 200 (Valid transition) ===");
        ApiResult result200 = api.changeStatus("CUS-1002", CustomerStatus.ACTIVE, correlationId);
        printResult(result200);

        System.out.println("=== Test 400 (Invalid Email and Blank name) ===");
        CustomerRequestDTO badEmailBlankName = new CustomerRequestDTO("CUS-2001", "", "bad-email", "ACTIVE");
        ApiResult resultBoth = api.create(badEmailBlankName, correlationId);
        printResult(resultBoth);

        System.out.println("\n=== Test 404 (Customer Not Found Again) ===");
        ApiResult result404Again = api.getById("CUS-9999", correlationId);
        printResult(result404Again);

//        System.out.println("\n=== Test 500 (throw RuntimeException) ===");
//
//        CustomerRepository brokenRepo = new InMemoryCustomerRepository() {
//            @Override
//            public Optional<Customer> findById(String customerId) {
//                throw new RuntimeException("");
//            }
//        };
//        CustomerService brokenService = new DefaultCustomerService(brokenRepo, new CustomerValidator(brokenRepo));
//        CustomerApiFacade brokenApi = new CustomerApiFacade(brokenService);
//
//        ApiResult result500 = brokenApi.getById("CUS-1001", correlationId);
//        printResult(result500);

//        System.out.println("\n=== Test 500 (EXPLOSION) ===");
//        throw new RuntimeException("EXPLOSION");

    }

    private static void printResult(ApiResult result) {
        switch (result) {
            case ApiResult.Ok ok -> System.out.println("WORKED");
            case ApiResult.Fail fail -> System.out.println(fail.error().toJson());
        }
    }
}