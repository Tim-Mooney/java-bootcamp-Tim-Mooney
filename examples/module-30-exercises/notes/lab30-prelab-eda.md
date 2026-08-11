# Lab 30 — Why Async for CRM

## Step 1 — List sync pain

Customer service creates `CUS-1001` Amina Khan over HTTP with correlation `lab-request-001`. List **three** problems if it also calls email, audit, and analytics synchronously in the same request thread.
- It couples the correlationID to 4 things and becomes harder to trace. Also, now the thread will take longer instead of just making more threads. Finally, if audit crashes, the entire thread crashes instead of just one of many threads.

## Step 2 — Event idea

In one sentence, describe publishing a `CustomerCreated` event so other teams consume independently.
- After CustomerService.create() commits CUS-1001, publish a CustomerCreated event (carrying the customer id, correlation id, and relevant fields) to a broker or event log so the email, audit, and analytics teams can each subscribe and process it on their own schedule, independently of the original request and of each other.

## Step 3 — Coupling check

Mark true/false: *The Customer JVM must be up for the Audit consumer to process an already-published event.*
- false
- 
## Scope
Pre-lab only — do not finish the full graded lab in this exercise.