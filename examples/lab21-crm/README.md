# Lab 21: Spring Boot REST API with Actuator Monitoring

This lab demonstrates Spring Boot Actuator for production-ready monitoring, health checks, and custom metrics in a REST API.

## Project Overview

**Lab 21** extends the CRM API with:
- Spring Boot Actuator for built-in monitoring endpoints
- Custom health indicators (readiness and liveness)
- Custom metrics collection with Micrometer
- Request tracking and performance measurements
- Production-ready observability features

## Prerequisites

- **Java**: 21 or higher
- **Maven**: 3.6 or higher
- **Git**: For version control

## Running the Application

### Start the Server

```bash
cd /Users/timmooney/java-bootcamp/examples/lab21-crm
mvn spring-boot:run
```

The application starts on `http://localhost:8080`. Actuator endpoints are available at `/actuator/`.

### Example API Calls

**Create a Customer:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "X-Correlation-Id: lab-request-001" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId":"CUS-3001",
    "fullName":"Michael Chen",
    "email":"michael.chen@example.com",
    "status":"ACTIVE"
  }'
```

**Get a Customer:**
```bash
curl http://localhost:8080/api/customers/CUS-3001
```

## Actuator Endpoints

### Health Check Endpoints

**Overall Health Status:**
```bash
curl http://localhost:8080/actuator/health
```

Response:
```json
{
  "status": "UP",
  "components": {
    "crmReadinessIndicator": {
      "status": "UP",
      "details": {
        "crm": "ready"
      }
    }
  }
}
```

**Readiness Probe (for Kubernetes):**
```bash
curl http://localhost:8080/actuator/health/readiness
```

**Liveness Probe (for Kubernetes):**
```bash
curl http://localhost:8080/actuator/health/liveness
```

### Metrics Endpoints

**All Available Metrics:**
```bash
curl http://localhost:8080/actuator/metrics
```

**Customer Creation Metrics:**
```bash
curl http://localhost:8080/actuator/metrics/crm.customer.create
```

Response includes tags for success/failure breakdown.

**Customer Get Metrics:**
```bash
curl http://localhost:8080/actuator/metrics/crm.customer.get
```

**Latency Metrics:**
```bash
curl http://localhost:8080/actuator/metrics/crm.customer.create.latency
curl http://localhost:8080/actuator/metrics/crm.customer.get.latency
```

### Other Useful Endpoints

**Application Info:**
```bash
curl http://localhost:8080/actuator/info
```

**Available Endpoints:**
```bash
curl http://localhost:8080/actuator
```

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Actuator Tests

```bash
mvn -Dtest=ActuatorIT test
```

### Test Readiness Toggle

The tests verify:
- Health status when CRM is ready
- Health status when CRM is not ready (OUT_OF_SERVICE)
- Metrics are properly collected
- Latency is tracked

## Project Structure

```
src/main/java/com/northstar/crm/
├── api/
│   └── CustomerController.java           # REST endpoints
├── health/
│   └── CrmReadinessIndicator.java        # Custom health indicator
├── logging/
│   └── CorrelationFilter.java            # Servlet filter for correlation ID
├── metrics/
│   └── CustomerMetrics.java              # Custom metrics collection
├── model/
│   └── Customer.java                     # Data model
├── repository/
│   ├── CustomerRepository.java           # Data access interface
│   └── InMemoryCustomerRepository.java   # In-memory implementation
├── service/
│   └── CustomerService.java              # Business logic
└── CrmApplication.java                   # Spring Boot entry point

src/test/java/com/northstar/crm/
└── actuator/
    └── ActuatorIT.java                   # Actuator integration tests
```

## Key Components

### CrmReadinessIndicator (Health Check)

Custom health indicator that:
- Reports readiness status of the CRM system
- Can be toggled for testing system degradation
- Returns `UP` when ready or `OUT_OF_SERVICE` when not
- Provides details about the system state

**Lab-Only Feature:** This readiness toggle is designed for testing and learning, not production use.

### CustomerMetrics (Custom Metrics)

Tracks:
- **crm.customer.create**: Counter with success/failure tags
- **crm.customer.get**: Counter with success/failure tags
- **crm.customer.create.latency**: Timer for create operation duration
- **crm.customer.get.latency**: Timer for get operation duration

### Correlation Filter

Automatically propagates correlation IDs for request tracing.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers/{id}` | Get a customer by ID |

## Actuator Management Endpoints

| Endpoint | Purpose |
|----------|---------|
| `/actuator/health` | Overall application health |
| `/actuator/health/readiness` | Readiness probe (Kubernetes) |
| `/actuator/health/liveness` | Liveness probe (Kubernetes) |
| `/actuator/metrics` | List all available metrics |
| `/actuator/metrics/{name}` | Details for specific metric |
| `/actuator/info` | Application info and build details |

## Metrics Explained

### Counter Metrics

**crm.customer.create[result=success]**: Number of successful customer creations
**crm.customer.create[result=failure]**: Number of failed customer creations
**crm.customer.get[result=success]**: Number of successful customer retrievals

### Timer Metrics

**crm.customer.create.latency**: Measures time taken to create a customer
- Provides count, mean, max, and percentiles

**crm.customer.get.latency**: Measures time taken to retrieve a customer
- Provides count, mean, max, and percentiles

## Health Status Reference

| Status | Meaning |
|--------|---------|
| UP | Component is working normally |
| DOWN | Component has failed |
| OUT_OF_SERVICE | Component is temporarily unavailable |
| UNKNOWN | Component status is unknown |

## Kubernetes Integration

These actuator endpoints are designed for Kubernetes probes:

```yaml
# In your Kubernetes deployment:
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
  initialDelaySeconds: 10
  periodSeconds: 5

readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
  initialDelaySeconds: 5
  periodSeconds: 10
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

Deletes lab21 artifacts from local Maven cache (optional).

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

**Actuator Endpoints Return 404:**
- Verify Spring Boot Actuator dependency is in pom.xml
- Check that the application started successfully
- Verify the port (default is 8080)

**Metrics Not Showing:**
- Make sure to make API calls (metrics are only recorded on requests)
- Check metrics endpoint: `curl http://localhost:8080/actuator/metrics`

**Health Status Shows DOWN:**
- Verify CrmReadinessIndicator is properly wired
- Check application logs for errors

**Tests Fail:**
- Clear Maven cache: `mvn clean`
- Rebuild: `mvn install`
- Verify Java version is 21+

## Key Concepts

- **Spring Boot Actuator**: Production-ready features for monitoring and management
- **Health Indicators**: Custom components that report application health status
- **Metrics**: Quantitative measurements for monitoring system performance
- **Micrometer**: Framework used by Spring Boot for metrics collection
- **Readiness/Liveness Probes**: Kubernetes patterns for managing pod lifecycle
- **Observability**: Ability to understand system behavior through logs, metrics, and traces

## Related Labs

- **Lab 19**: REST API with basic validation
- **Lab 20**: Structured logging with correlation IDs

## Notes

- Actuator provides many endpoints; configure which are exposed in production
- Metrics data is in-memory and reset when application restarts
- Health indicators should be fast to respond (avoid blocking operations)
- CrmReadinessIndicator is a lab-only feature for teaching; use real health checks in production
- Micrometer supports exporting metrics to Prometheus, Grafana, and other tools
