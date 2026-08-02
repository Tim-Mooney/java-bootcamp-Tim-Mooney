# Lab 14 — CRM with DTOs & Validation

CRM service with request/response DTOs and Jakarta Validation constraints.

## How to Run Main

```bash
cd examples/lab14-crm
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java -Dexec.mainClass=com.northstar.crm.Main
```

Or compile and run:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

## Validation Rules

CustomerRequestDTO enforces:
- **customerId**: Required, max 32 characters
- **fullName**: Required, 2-100 characters
- **email**: Required, valid email format, max 254 characters
- **status**: Required, 1-32 characters

## Sample Request

```json
{
  "customerId": "CUS-1001",
  "fullName": "Amina Khan",
  "email": "amina.khan@example.com",
  "status": "ACTIVE"
}
```

## How to Run Tests

```bash
mvn clean test
```

## Build & Package

```bash
mvn clean package
java -jar target/customer-service.jar
```

## Cleanup

Remove build artifacts:
```bash
mvn clean
```

This deletes the `target/` directory and all compiled classes.

## Validation rules (CustomerRequestDTO)

| Field | Constraints |
| ----- | ----------- |
| customerId | @NotBlank, @Size(max=32) |
| fullName | @NotBlank, @Size(2..100) |
| email | @NotBlank, @Email, @Size(max=254) |
| status | @NotBlank (ACTIVE\|PROSPECT\|SUSPENDED\|CLOSED) |

## Sample invalid (email)

email=not-an-email → IllegalArgumentException with field message
correlationId=lab-request-001