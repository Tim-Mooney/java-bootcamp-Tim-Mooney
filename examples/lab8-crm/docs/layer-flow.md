React, Kafka, and PostgreSQL are out of scope for lab 8.

Client sends a create request (CustomerRequest) with customerId CUS-1001, fullName, status, email, phone, and a correlationId lab-request-001.
The controller (CustomerController) accepts the request and calls createCustomer(). Validation happens at the controller layer, but not in scope.
In createCustomer(), customerService.create() is called with the request DTO and correlationId.
The service layer (CustomerService) creates the entity (Customer) and handles the checks like unique ID and business logic.
From here, the service layer calls the repository (CustomerRepository) to save the Customer.
The repository DOES NOT STORE THE DTO!
The service layer also maps the customer back into a response DTO (CustomerResponse) and returns it to the controller with the same customerId and correlationId.

