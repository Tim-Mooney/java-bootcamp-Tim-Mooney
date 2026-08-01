# Lab 9 — CRM Service (Persistence & Validation)

## Overview

Lab 9 extends Lab 8 with persistent storage and comprehensive validation. It demonstrates:
- File-based repository implementation (replacing in-memory stubs)
- Input validation (Jakarta Validation API)
- Custom exception handling
- DTOs for request/response mapping

## How to Run

### Compile & Run Tests

```bash
cd examples/lab9-crm
mvn clean test
```

### Compile & Build JAR

```bash
mvn clean package
```

### Run the Application

```bash
java -jar target/customer-service.jar
```

Or skip tests:

```bash
mvn -DskipTests package
java -jar target/customer-service.jar
```

### Run via Maven (without JAR)

```bash
mvn -DskipTests compile exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

## Cleanup

Remove build artifacts:

```bash
mvn clean
```

This deletes:
- `target/` directory (compiled classes, JAR)
- Local repository cache for this project
- All generated files

## CI Note (Preview — Pipelines Deepen in Later Modules)

Preferred verify command on agents:

```bash
mvn -B verify
```

`-B` is batch mode (non-interactive). Prefer `verify` over `install` on CI
unless your pipeline intentionally publishes to an artifact repository.
Never deploy snapshots from a developer laptop without agreement.

## Reference

Artifact coordinates: com.northstar:customer-service:0.1.0-SNAPSHOT
Sample customer IDs (docs only): CUS-1001, CUS-1002
Correlation ID (logs later): lab-request-001