# Lab 32 — Why Resilience

## Step 1 — Scenario

Customer detail for `CUS-1001` Amina calls Account Profile. The dependency hangs 30s. List three user-visible or thread-pool effects.
- Nothing will show up for 30 seconds and the user will get mad.
- If the threads can't run concurrently, every other user calling Account Profile will wait too.
- If anything needs the result of that call it will also be forced to wait.
- Health checks may start failing.

## Step 2 — Pattern names

- Resilience4j ideas: retry, circuit breaker, time limiter, fallback.

## Step 3 — Not a substitute

- resilience wraps calls; it does not fix a permanently wrong URL.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.