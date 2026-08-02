# Lab 16 — CRM: Business Exceptions & API Results

Quick demo of BusinessException handling and ApiResult responses (400/404/409) with correlation id tracing.

## Run Main (quick)

```bash
cd examples/lab16-crm
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java -Dexec.mainClass=com.northstar.crm.Main
```

Or compile & run:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

## Validation Rules (CustomerRequestDTO)

- customerId: required, max 32 chars
- fullName: required, 2–100 chars
- email: required, valid email
- status: required, 1–32 chars

Sample valid request:

```json
{
  "customerId": "CUS-1001",
  "fullName": "Amina Khan",
  "email": "amina.khan@example.com",
  "status": "ACTIVE"
}
```

## What the demo shows

- 400 — validation failures return ApiResult.Fail (validation errors)
- 404 — missing customer returns Not Found mapped to ApiResult.Fail
- 409 — business conflict (invalid transition) returned as ApiResult.Fail

Errors include a correlation id (lab-request-001) for tracing.

## Run tests / build

```bash
mvn clean test
mvn clean package
```

## Cleanup

```bash
mvn clean
```

This removes the `target/` directory and generated files.

## Notes

- The module uses an in-memory repository for demos; not production-ready.
- BusinessException is mapped to structured error responses by GlobalExceptionHandler.
