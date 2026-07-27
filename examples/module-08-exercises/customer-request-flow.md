**CUSTOMER**
Name: Amina Khan
Email: amina@example.test
Requested status: ACTIVE
Correlation ID: lab-request-001


```mermaid
sequenceDiagram
    actor Client
    participant C as CustomerController
    participant S as CustomerService
    participant R as CustomerRepository

    Client->>C: CustomerRequest
    C->>S: createCustomer(request)
    S->>S: validate + assign ID/status
    S->>R: save(Customer)
    R-->>S: saved Customer
    S-->>C: CustomerResponse
    C-->>Client: response
```

| Boundary | Input | Output |
| -------- | ----- | ------ |
| Client → controller | Future transport payload | `CustomerRequest` |
| Service validation | Request DTO | valid domain values |
| Service → repository | `Customer` entity | saved entity |
| Service → controller | entity/result | `CustomerResponse` |

```mermaid
sequenceDiagram
    actor Client
    participant C as Controller
    participant S as Service

    Client->>C: blank name
    C->>S: createCustomer(request)
    S-->>C: validation failure
    C-->>Client: safe error response later
```

## Now
- Package names and stub responsibilities
- Plain Java types that compile
- Documented flow

## Later
- Spring controller annotations
- Validation annotations
- Repository implementation/JPA
- HTTP response mapping
- Correlation-ID logging