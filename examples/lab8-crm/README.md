# Lab 8 — CRM Service

Overview
--------
Lab 8 implements a small CRM-style customer service that demonstrates separation of concerns, layered architecture, and testable code via stubs.

Key components:
- `entity` — domain model classes (Customer, CustomerStatus)
- `repository` — data access abstraction (in-memory/stub implementations)
- `service` — business logic and validation
- `api` — thin facade that accepts DTOs and returns ApiResult objects
- `dto` — request/response DTOs and mapping utilities

Why layers?
-----------
- Separation of concerns: each layer has a focused responsibility, making code easier to reason about and change.
- Testability: services and repositories can be tested in isolation by substituting in-memory or stub implementations.
- Replaceability: when ready, the repository layer can be swapped for a real database without touching business logic or API code.

Why stubs/in-memory?
--------------------
- Fast feedback during development and tests (no DB setup required).
- Deterministic behavior for unit tests.
- Small teams can iterate on the service and API layers before integrating external systems.

How to compile and run
----------------------
This module uses Maven. Ensure JDK 21+ and Maven are installed.

1. Build and run tests:

```bash
cd examples/lab8-crm
mvn clean test
```

2. Run the main demo (if present):

```bash
mvn -DskipTests package
java -jar target/customer-service.jar
```

Link to docs
------------
- See `docs/` in the module for design diagrams and API examples.

Notes
-----
- Follow-up labs introduce persistence and exception handling refinements.
