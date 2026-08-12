# Lab 32 — Pattern Map

## Reference

| Pattern | CRM use |
| --- | --- |
| Retry | Transient 503 from Account Profile |
| TimeLimiter | Fail fast if call exceeds N ms |
| CircuitBreaker | Stop calling when failure rate high |
| Fallback | Return cached/minimal profile for Amina |

## Step 2 — Add Ravi row

Add one example sentence for `CUS-1002` Ravi when circuit is open.
Call Account details, wait a little bit (unless its already open), see a message saying its temporarily down.

## Step 3 — Order idea

call -> fail -> TimeLimiter → CircuitBreaker → Retry → call)

## Step 4 — Boundary

do not apply circuit breaker to local in-memory map lookups.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.