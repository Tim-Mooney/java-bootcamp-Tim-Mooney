# Lab 17 — JUnit / JaCoCo runbook

## Commands

```bash
mvn -B test
mvn -B clean verify
```

## Coverage gate

- Package: `com.northstar.crm.service`
- Minimum line ratio: **0.80**
- Deliberately fail once with `0.99`, then restore `0.80`

## Testing

target/surefire-reports/
target/site/jacoco/index.html

tests: GlobalExceptionHandlerTest.java, CustomerServiceTests.java, CustomerValidatorParameterizedTest.java
Coverage goal: >= 80% 
Copilot review: Ensure tests can fail, shared fixture IDs, no phantom imports, and independent @BeforeEach
Last branch: I added a test for empty user ID to close the last branch and reach 96% coverage.


