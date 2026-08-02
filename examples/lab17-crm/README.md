# Lab 17 — CRM Tests & Coverage

Simple module focused on JUnit service tests and business-exception behavior.

## How to Run Main (quick)

```bash
cd examples/lab17-crm
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

This lab's tests cover duplicate ID/email conflicts (BusinessException), listAll(), and correlation-id propagation. Test fixtures: CUS-1001 (Amina), CUS-1002 (Ravi).

## Build & Package

```bash
mvn clean package
java -jar target/customer-service.jar
```

## Cleanup

```bash
mvn clean
```

Notes

- No Spring — pure Java and JUnit.
- Coverage checks (JaCoCo) may be configured in `pom.xml` for a minimum threshold.

