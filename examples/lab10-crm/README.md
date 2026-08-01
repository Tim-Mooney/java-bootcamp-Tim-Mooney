# Lab 10 — Plain Java CRM Service

Simple CRM service demonstrating plain Java design without Spring or JPA.

## How to Run

Compile and run:
```bash
cd examples/lab10-crm
mvn clean compile
java -cp target/classes com.northstar.crm.Main
```

Or with Maven:
```bash
mvn -DskipTests exec:java -Dexec.mainClass="com.northstar.crm.Main"
```

## Cleanup

Remove build artifacts:
```bash
mvn clean
```

This deletes the `target/` directory and all compiled classes.
