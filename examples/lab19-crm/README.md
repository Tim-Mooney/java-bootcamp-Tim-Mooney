# Lab 19: Spring Boot REST API with Request Validation

This lab demonstrates a Spring Boot REST API for a Customer Relationship Management (CRM) system with HTTP request validation, correlation IDs for request tracing, and integration testing.

## Project Overview

**Lab 19** builds a REST API with:
- Spring Boot REST controller for customer CRUD operations
- Input validation (customer ID and name are required)
- Request correlation ID tracking for tracing
- In-memory repository implementation
- Integration tests using Spring Boot Test

## Prerequisites

- **Java**: 21 or higher
- **Maven**: 3.6 or higher
- **Git**: For version control

## Running the Application

### Start the Server

```bash
cd /Users/timmooney/java-bootcamp/examples/lab19-crm
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### Example API Calls

**Create a Customer:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "X-Correlation-Id: lab-request-001" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId":"CUS-1001",
    "fullName":"Amina Khan",
    "email":"amina.khan@example.com",
    "status":"ACTIVE"
  }'
```

**Get a Customer:**
```bash
curl http://localhost:8080/api/customers/CUS-1001
```

**Error: Missing Customer Name (400 Bad Request):**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "customerId":"CUS-1901",
    "fullName":"",
    "email":"test@example.com",
    "status":"PROSPECT"
  }'
```

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn -Dtest=CustomerApiIT test
```

### Run with Maven Verify (includes build)

```bash
mvn verify
```

## Project Structure

```
src/main/java/com/northstar/crm/
├── api/
│   └── CustomerController.java       # REST endpoints
├── model/
│   └── Customer.java                 # Data model
├── repository/
│   └── CustomerRepository.java       # Data access interface
│   └── InMemoryCustomerRepository.java  # In-memory implementation
├── service/
│   └── CustomerService.java          # Business logic
└── CrmApplication.java               # Spring Boot entry point

src/test/java/com/northstar/crm/
└── integration/
    └── CustomerApiIT.java            # Integration tests
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers/{id}` | Get a customer by ID |

### Request Headers

- **X-Correlation-Id** (optional): Unique request identifier for tracing. Defaults to "lab-request-001" if not provided.

### Response Headers

- **X-Correlation-Id**: Echo of the request correlation ID in the response.

## Validation Rules

The API validates the following:
- **customerId**: Required, cannot be blank
- **fullName**: Required, cannot be blank
- **email**: Optional
- **status**: Optional

If validation fails, the API returns:
- **HTTP 400 Bad Request**: When required fields are missing or blank
- **HTTP 201 Created**: When customer is successfully created
- **HTTP 200 OK**: When customer is successfully retrieved
- **HTTP 404 Not Found**: When customer does not exist

## Cleanup Instructions

### Stop the Running Server

Press `Ctrl + C` in the terminal running `mvn spring-boot:run`.

### Clean Build Artifacts

```bash
mvn clean
```

This removes the `target/` directory and all compiled classes.

### Remove All Maven Dependencies

```bash
rm -rf ~/.m2/repository/com/northstar
```

This deletes the lab19 artifacts from your local Maven cache (optional).

### Clean IDE Cache (IntelliJ IDEA)

If using IntelliJ IDEA, invalidate caches:
1. **File** → **Invalidate Caches**
2. **Invalidate and Restart**

### Full Reset

For a complete fresh start:

```bash
mvn clean
rm -rf .idea/
rm -rf *.iml
```

## Troubleshooting

**Port 8080 Already in Use:**
```bash
# Kill the process using port 8080
lsof -ti:8080 | xargs kill -9
```

**Tests Fail with Connection Error:**
- Ensure no other application is using port 8080
- Clear Maven cache: `mvn clean`
- Rebuild: `mvn install`

**Import Errors in IDE:**
- Refresh Maven project: Right-click project → **Maven** → **Reload Projects**
- Invalidate caches and restart IDE

## Key Concepts

- **Spring Boot**: Simplified Spring application setup and configuration
- **REST API**: HTTP-based API following REST principles
- **Input Validation**: Server-side validation to ensure data integrity
- **Correlation ID**: Unique identifier for request tracing across systems
- **Integration Testing**: Testing complete application stack (controller → service → repository)
- **In-Memory Storage**: Non-persistent data storage for testing

## Related Labs

- **Lab 18**: Service Layer with Mockito and BDD testing
- **Lab 20**: Database persistence with Spring Data JPA

## Notes

- Data is stored in-memory and lost when the application stops
- This is for educational purposes and not production-ready
- Correlation IDs help with debugging distributed systems
