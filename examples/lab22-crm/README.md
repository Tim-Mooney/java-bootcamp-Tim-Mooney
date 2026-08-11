# Lab 22: Spring Dependency Injection & Component Stereotypes

This lab demonstrates Spring Framework's dependency injection patterns, component stereotypes (`@Service`, `@Repository`), and bean lifecycle management using annotations.

## Project Overview

**Lab 22** focuses on core Spring concepts:
- Constructor injection with final fields
- Spring stereotypes (`@Service`, `@Repository`)
- Bean lifecycle with `@PostConstruct` and `@PreDestroy`
- Automatic bean wiring and dependency resolution
- Notificationservice integration with async-like behavior
- Building a complete DI container without manual wiring

## Prerequisites

- **Java**: 21 or higher
- **Maven**: 3.6 or higher
- **Git**: For version control

## Running the Application

### Start the Server

```bash
cd /Users/timmooney/java-bootcamp/examples/lab22-crm
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

### Observe Startup Logs

When the server starts, you'll see:
```
CustomerService ready
```

This comes from the `@PostConstruct` lifecycle method.

### Example API Calls

**Create a Customer:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "X-Correlation-Id: lab-request-001" \
  -H "Content-Type: application/json" \
  -d '{
    "id":"CUS-1001",
    "name":"Amina Khan",
    "email":"amina.khan@example.com",
    "status":"ACTIVE"
  }'
```

The API will respond with 201 CREATED, and approximately 10 seconds later (after `NotificationService` completes), you'll see:
```
customer.created id=CUS-1001 correlationId=lab-request-001
```

**Get a Customer:**
```bash
curl http://localhost:8080/api/customers/CUS-1001
```

Response:
```json
{
  "id": "CUS-1001",
  "name": "Amina Khan",
  "email": "amina.khan@example.com",
  "status": "ACTIVE"
}
```

### Observe Shutdown Logs

When you stop the server (Ctrl + C), you'll see:
```
CustomerService shutting down
```

This comes from the `@PreDestroy` lifecycle method.

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Unit Test (No Spring Context)

```bash
mvn -Dtest=CustomerServiceTest test
```

This test demonstrates that the code works with manual instantiation (not relying on Spring):
```java
var repo = new InMemoryCustomerRepository();
var notify = new NotificationService();
var service = new CustomerService(repo, notify);
```

## Project Structure

```
src/main/java/com/northstar/crm/
├── api/
│   └── CustomerController.java           # REST endpoints (wired with @RestController)
├── model/
│   └── Customer.java                     # Data model
├── repository/
│   ├── CustomerRepository.java           # Data access interface
│   └── InMemoryCustomerRepository.java   # Implementation (@Repository)
├── service/
│   ├── CustomerService.java              # Business logic (@Service)
│   └── NotificationService.java          # Async notifications (@Service)
└── CrmApplication.java                   # Spring Boot entry point

src/test/java/com/northstar/crm/
└── CustomerServiceTest.java              # Unit test (no Spring context)
```

## Key Components

### CustomerService (@Service)

```java
@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final NotificationService notificationService;
  
  public CustomerService(CustomerRepository customerRepository, 
                        NotificationService notificationService) {
    this.customerRepository = customerRepository;
    this.notificationService = notificationService;
  }
  
  @PostConstruct
  void init() {
    log.info("CustomerService ready");
  }
  
  @PreDestroy
  void shutdown() {
    log.info("CustomerService shutting down");
  }
}
```

**Key Points:**
- `@Service` marks this as a managed Spring bean
- Constructor injection with final fields (immutable, thread-safe)
- Spring automatically resolves `CustomerRepository` and `NotificationService` dependencies
- `@PostConstruct` runs after bean creation
- `@PreDestroy` runs before bean destruction

### NotificationService (@Service)

```java
@Service
public class NotificationService {
  public void notifyCreated(String customerId, String correlationId) {
    try {
      Thread.sleep(10000);  // Simulates async work
      log.info("customer.created id={} correlationId={}", customerId, correlationId);
    } catch(InterruptedException e) {
      log.info("no notify");
    }
  }
}
```

**Purpose:** Demonstrates asynchronous notification behavior in a separate service.

### InMemoryCustomerRepository (@Repository)

```java
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
  // Implements data access logic
}
```

**Key Points:**
- `@Repository` marks this as a data access component
- Spring manages the bean lifecycle
- Can be easily swapped for database implementation

## Dependency Injection Flow

```
Spring Container starts
    ↓
Detects @Service, @Repository, @RestController stereotypes
    ↓
Creates instances using constructor injection
    ↓
CustomerService(CustomerRepository, NotificationService)
    ↓
Calls @PostConstruct on CustomerService
    ↓
Application ready
    ↓
On shutdown: Calls @PreDestroy
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers/{id}` | Get a customer by ID |

### Request/Response

**Create Request:**
- Headers: `X-Correlation-Id` (optional, defaults to "lab-request-001")
- Body: JSON Customer object
- Response: 201 CREATED with Customer JSON

**Get Request:**
- Response: 200 OK with Customer JSON

## Spring Stereotypes

| Annotation | Purpose | Scope |
|------------|---------|-------|
| `@Service` | Business logic beans | Usually singleton |
| `@Repository` | Data access beans | Usually singleton |
| `@Component` | Generic managed bean | Usually singleton |
| `@RestController` | REST endpoint handler | Usually singleton |

All stereotypes inherit from `@Component` and create singletons by default.

## Lifecycle Annotations

| Annotation | Timing | Usage |
|-----------|--------|-------|
| `@PostConstruct` | After bean construction | Initialize resources, warm caches |
| `@PreDestroy` | Before bean destruction | Clean up resources, log shutdown |

## Bean Wiring Resolution

When Spring encounters:
```java
public CustomerService(CustomerRepository customerRepository, 
                      NotificationService notificationService) { ... }
```

Spring:
1. Looks for a bean of type `CustomerRepository`
2. Finds `InMemoryCustomerRepository` (implements the interface)
3. Looks for a bean of type `NotificationService`
4. Finds the `@Service NotificationService`
5. Instantiates both and injects into `CustomerService`

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

Deletes lab22 artifacts from local Maven cache (optional).

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

**No Bean of Type CustomerRepository Found:**
- Verify `InMemoryCustomerRepository` has `@Repository` annotation
- Verify it implements `CustomerRepository` interface
- Check for typos in class names

**CustomerService Not Autowired:**
- Verify `CustomerService` has `@Service` annotation
- Verify it has a public constructor with dependencies
- Check that dependencies also have stereotypes (`@Service`, `@Repository`)

**@PostConstruct Not Being Called:**
- Add `import jakarta.annotation.PostConstruct;` (not `javax.annotation`)
- Verify method is public or package-private (not private)
- Verify method takes no arguments and returns void

**Tests Fail:**
- Clear Maven cache: `mvn clean`
- Rebuild: `mvn install`
- Verify Java version is 21+

## Key Concepts

- **Dependency Injection**: Spring automatically provides dependencies to beans
- **Stereotypes**: Annotations that mark classes as Spring-managed beans
- **Constructor Injection**: Preferred method for required dependencies (immutable)
- **Bean Lifecycle**: `@PostConstruct` and `@PreDestroy` for initialization/cleanup
- **Singleton Scope**: Default for Spring beans (one instance per context)
- **Inversion of Control**: Application code depends on abstractions, Spring handles wiring
- **Loose Coupling**: Services depend on interfaces, not concrete implementations

## Related Labs

- **Lab 19**: REST API with basic validation
- **Lab 20**: Structured logging with correlation IDs
- **Lab 21**: Spring Boot Actuator monitoring
- **Lab 23**: Advanced Spring patterns (AOP, proxies, etc.)

## Notes

- The `NotificationService` simulates async work with `Thread.sleep(10000)` for demo purposes
- In production, use `@Async`, message queues, or reactive patterns
- Constructor injection is preferred over field injection (enables immutability and testing)
- Spring stereotypes are the foundation of Spring's component scanning and auto-wiring
- Bean lifecycle methods are useful for resource management (DB connections, thread pools, etc.)
