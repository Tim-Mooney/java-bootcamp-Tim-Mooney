# Lab 13 — CRM with Address Handling

CRM service with customer address support. **Note: address is non-live** (for development/testing only).

## How to Run

Compile and run tests:
```bash
cd examples/lab13-crm
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

| # | Confirm | Your notes     |
| - | ------- |----------------|
| 1 | Namespace URI published | Pass           |
| 2 | WSDL location placeholder documented | Pass           |
| 3 | Three operations named and described | Pass           |
| 4 | Sample success envelopes for CUS-1001 / CUS-1002 | Pass           |
| 5 | Fault examples for not-found and validation | Pass           |
| 6 | Correlation ID convention (`lab-request-001` style) | Pass           |
| 7 | Explicit note: implementation arrives in Lab 24 | Pass           |
| 8 | Optional: screenshot of VS Code XSD/WSDL outline | IM ON INTELLIJ |

