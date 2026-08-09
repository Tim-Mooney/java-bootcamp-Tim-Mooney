# Lab 22 — Dependency graph

## Bean edges (fill in)

- `CrmApplication` scans `com.northstar.crm`
- `CustomerController` → `CustomerService`
- `CustomerService` → `CustomerRepository` / `InMemoryCustomerRepository`
- `CustomerService` → `NotificationService`

## Fixtures

- `CUS-1001` Amina Khan ACTIVE
- `CUS-1002` Ravi Singh PROSPECT
- Correlation: `lab-request-001`
- All default singleton.
- Correlation: X-Correlation-Id / lab-request-001 
- Lab IDs: CUS-1001, CUS-1002 
- Anti-pattern: new InMemoryCustomerRepository() inside CustomerService

## Why constructor injection

Constructor injection makes dependencies explicit and required, so CustomerService can't even be instantiated without a CustomerRepository and NotificationService — the compiler enforces it, unlike field injection where a missing dependency silently stays null until it NPEs at runtime. It also lets you write plain unit tests with new CustomerService(repo, notify) and pass in test doubles directly, without needing a Spring context, reflection, or a testing framework's DI support at all — which is exactly what CustomerServiceTest already does. Finally, final fields plus a constructor mean the object is fully valid the moment it's built, so there's no window where a half-wired bean can be called before Spring finishes injecting it.

