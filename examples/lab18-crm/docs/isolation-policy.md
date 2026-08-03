# Lab 18 — isolation policy

## What we mock

- `CustomerRepository` (I/O boundary)

## What we keep real

- `CustomerValidator` (domain rules)
- `DefaultCustomerService` (class under test — never mock it)

## QUESTIONS

I prefer lab 17 tests when I need to see if things are working together. Lab 18 style tests 
are useful for making sure every component of the overall system works in isolation, lab 17 style 
makes sure everything works when it's all wired together.

## Which tests use real in-memory repo (Lab 17 style) vs mocks (Lab 18 unit)
CustomerServiceTests.java uses a real in-memory repo, the other test files use mocks.
## How to choose stub (when/given) vs verify (verify/then().should)
Stub is what the method in a mocked class should return under some circumstance.
Like if the method being tested needs getById to return false, stub getById to return false when called with some ID.

Verify is when you need to see if some call did or did not happen.
## Correlation ID expectations on exception paths
Correlation ID is fixed to "lab-request-001" right now and is appended to all BusinessExceptions
## Why both styles can coexist
The styles are just to test different things. Lab 17 style tests that everything works together. If any part fails, the entire test may fail.

Lab 18 style is to focus on one class or method to ensure that it works before moving onto Lab 17 style tests to make sure everything works together.
Lab 18 tests in a vacuum, Lab 17 tests everything together.
