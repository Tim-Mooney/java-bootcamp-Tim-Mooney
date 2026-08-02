package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;

public class Main {
    public static void main(String[] args) {
        // TODO: one shared InMemoryCustomerRepository for validator + service
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);

        Customer amina = Customer.amina();
        Customer ravi = Customer.ravi();

        service.addCustomer(amina); // ACTIVE
        service.addCustomer(ravi);  // PROSPECT

        Customer activated = service.changeStatus(
                "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        System.out.printf("activated %s status=%s%n",
                activated.getCustomerId(), activated.getStatus());

        try {
            service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1001 still: " + service.findById("CUS-1001").orElseThrow().getStatus());
        try {
            service.changeStatus("CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1002 still: " + service.findById("CUS-1002").orElseThrow().getStatus());

        Customer closed = service.changeStatus(
                "CUS-1002", CustomerStatus.CLOSED, "lab-request-001");
        System.out.printf("closed %s status=%s%n",
                closed.getCustomerId(), closed.getStatus());
        try {
            service.changeStatus("CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        } catch (IllegalStateException ex) {
            System.out.println("expected failure: " + ex.getMessage());
        }
        System.out.println("CUS-1002 still: " + service.findById("CUS-1001").orElseThrow().getStatus());

    }
}
