# Lab 22 — Constructor Injection Preference

## Preferred pattern
constructor with final CustomerRepository + NotificationService.

## Why (testability)
required deps explicit; unit test = new CustomerService(fakeRepo, fakeNotifier).

## Avoid
field @Autowired as primary pattern.

## Setter role (one line)
optional only — not Lab 22 primary wiring.

## Scope
Pre-lab only.