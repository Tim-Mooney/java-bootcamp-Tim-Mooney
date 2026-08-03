# Lab 18 — Mockito isolation tests

**Theme:** Mock CustomerRepository for DefaultCustomerService; verify behavior without Spring.

## Run main

```bash
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java -Dexec.mainClass=com.northstar.crm.Main
```

## Build & test

```bash
mvn clean test
mvn clean verify
```

## Cleanup

```bash
rm -rf target/
```

## Test coverage

- **CustomerServiceMockitoTest**
  - `activatesProspectUsingStubbedRepository()` — Mock findById + save, verify status updated
  - `unknownCustomerDoesNotSave()` — Mock findById→empty, verify throws + never saves
  - `addCustomerCapturesSavedEntity()` — Mock existsById/existsByEmail, capture saved Customer
  - `addCustomerWithDuplicateEmailThrowsAndNeverSaves()` — Mock existsByEmail→true, verify throws + never saves
- **CustomerServiceBddMockTest**
  - BDD-style given/when/then with Mockito mocks

No Spring annotations. Plain Java service mocking with @Mock, @ExtendWith(MockitoExtension.class), when/verify/ArgumentCaptor.
