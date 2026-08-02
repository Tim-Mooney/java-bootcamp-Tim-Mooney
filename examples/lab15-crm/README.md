# Lab 15 — CRM Business Exceptions & Error Handling

Small CRM module focusing on business exception handling and a consistent error API.

> Short — how to run and clean up, with build/test commands.

## How to Run Main (quick)

From the module root:
```bash
cd examples/lab15-crm
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java -Dexec.mainClass=com.northstar.crm.Main
```

Or compile & run:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

## Run Tests

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

This removes the `target/` directory and generated files.

## Notes

- This lab introduces `BusinessException` and a `GlobalExceptionHandler` that map domain errors to structured error responses.
- Keep the exception messages free of secrets — only correlation IDs for tracing.

