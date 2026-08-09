# Lab 20: Spring Boot REST API with Structured Logging

This lab demonstrates structured logging in a Spring Boot REST API using correlation IDs and MDC (Mapped Diagnostic Context) for distributed request tracing and debugging.

## Project Overview

**Lab 20** extends the CRM API with:
- Structured logging using SLF4j and MDC (Mapped Diagnostic Context)
- Correlation ID propagation through all layers (web filter → service → logging)
- Custom servlet filter for automatic header management
- Correlation ID preservation across log statements
- Integration tests for logging verification

## Prerequisites

- **Java**: 21 or higher
- **Maven**: 3.6 or higher
- **Git**: For version control

## Running the Application

### Start the Server

```bash
cd /Users/timmooney/java-bootcamp/examples/lab20-crm
mvn spring-boot:run
```

The application will start on `http://localhost:8080`. You'll see structured log output with correlation IDs embedded.

### Example API Calls

**Create a Customer (with logging):**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "X-Correlation-Id: my-request-12345" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId":"CUS-2001",
    "fullName":"Sarah Johnson",
    "email":"sarah.j@example.com",
    "status":"ACTIVE"
  }'
```

**Get a Customer:**
```bash
curl -H "X-Correlation-Id: my-request-12345" \
  http://localhost:8080/api/customers/CUS-2001
```

### Expected Log Output

Each request will log with the correlation ID throughout:
```
2026-08-09T13:40:00.123-04:00 INFO  [...] [my-request-12345] Creating customer CUS-2001
2026-08-09T13:40:00.234-04:00 INFO  [...] [my-request-12345] Customer created successfully
```

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Logging Tests

```bash
mvn -Dtest=CustomerLoggingIT test
```

### Verify Correlation IDs in Logs

The integration test verifies that:
- Correlation ID is extracted from request headers
- Default correlation ID is used when header is missing
- Correlation ID is echoed in response headers
- Correlation ID is available in MDC throughout the request lifecycle

## Project Structure

```
src/main/java/com/northstar/crm/
├── api/
│   └── CustomerController.java           # REST endpoints
├── logging/
│   └── CorrelationFilter.java            # Servlet filter for correlation ID
├── model/
│   └── Customer.java                     # Data model
├── repository/
│   ├── CustomerRepository.java           # Data access interface
│   └── InMemoryCustomerRepository.java   # In-memory implementation
├── service/
│   └── CustomerService.java              # Business logic
└── CrmApplication.java                   # Spring Boot entry point

src/test/java/com/northstar/crm/
└── logging/
    └── CustomerLoggingIT.java            # Logging integration tests
```

## Key Components

### CorrelationFilter (Servlet Filter)

The `CorrelationFilter` automatically:
- **Extracts** `X-Correlation-Id` header from incoming requests
- **Defaults** to "lab-request-001" if header is missing
- **Stores** correlation ID in MDC (Mapped Diagnostic Context)
- **Echoes** correlation ID in response headers
- **Clears** MDC after request completes (in finally block)

### Correlation ID Flow

```
HTTP Request
    ↓
CorrelationFilter (extract from header, store in MDC)
    ↓
CustomerController (access from MDC if needed)
    ↓
CustomerService (use for logging)
    ↓
All log statements (automatically include correlation ID)
    ↓
HTTP Response (echo header)
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers/{id}` | Get a customer by ID |

### Request/Response Headers

- **X-Correlation-Id** (optional): Unique request identifier. Defaults to "lab-request-001".
- Response echoes the same correlation ID for tracing.

## Validation Rules

- **customerId**: Required, cannot be blank
- **fullName**: Required, cannot be blank
- **email**: Optional
- **status**: Optional

HTTP Status Codes:
- **201 Created**: Customer successfully created
- **200 OK**: Customer successfully retrieved
- **404 Not Found**: Customer does not exist
- **400 Bad Request**: Validation failed

## Logging Configuration

Logs include correlation ID prefix from MDC:

```
[TIMESTAMP] [LEVEL] [THREAD] [correlationId] MESSAGE
```

Example:
```
2026-08-09 13:40:00.123 INFO [http-nio-8080-exec-1] [request-abc-123] Customer service initialized
2026-08-09 13:40:00.234 INFO [http-nio-8080-exec-1] [request-abc-123] Creating customer CUS-2001
2026-08-09 13:40:00.345 INFO [http-nio-8080-exec-1] [request-abc-123] Customer saved successfully
```

## Cleanup Instructions

### Stop the Running Server

Press `Ctrl + C` in the terminal running `mvn spring-boot:run`.

### Clean Build Artifacts

```bash
mvn clean
```

Removes `target/` directory and compiled classes.

### Remove Maven Dependencies

```bash
rm -rf ~/.m2/repository/com/northstar
```

Deletes lab20 artifacts from local Maven cache (optional).

### Clean IDE Cache (IntelliJ IDEA)

1. **File** → **Invalidate Caches**
2. **Invalidate and Restart**

### Full Reset

```bash
mvn clean
rm -rf .idea/
rm -rf *.iml
```

## Troubleshooting

**Port 8080 Already in Use:**
```bash
lsof -ti:8080 | xargs kill -9
```

**Correlation ID Not Appearing in Logs:**
- Verify the `X-Correlation-Id` header is being sent
- Check that `CorrelationFilter` is registered as a Spring component
- Ensure logging configuration includes MDC pattern

**Tests Fail:**
- Clear Maven cache: `mvn clean`
- Rebuild: `mvn install`
- Verify Java version is 21+

## Key Concepts

- **MDC (Mapped Diagnostic Context)**: Thread-local storage for request-specific data
- **Servlet Filter**: Intercepts all HTTP requests and responses
- **Correlation ID**: Unique identifier for tracing requests through distributed systems
- **Structured Logging**: Adding contextual data to log statements for better debugging
- **Request Tracing**: Following a single user request through multiple services

## Related Labs

- **Lab 19**: REST API with basic validation
- **Lab 21**: Monitoring and metrics with Spring Boot Actuator

## Notes

- Correlation IDs are essential for debugging in microservices
- MDC is thread-safe and automatically cleared to prevent memory leaks
- The filter runs on every request, so performance impact is minimal
- This implementation is suitable for small to medium-scale applications
