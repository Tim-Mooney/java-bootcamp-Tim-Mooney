# Lab 12 — CRM Repository Pattern

CRM service with repository abstraction for cleaner data access.

## How to Run

Compile and run tests:
```bash
cd examples/lab12-crm
mvn clean test
```

Run the application:
```bash
mvn -DskipTests compile exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

Or package and run:
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
