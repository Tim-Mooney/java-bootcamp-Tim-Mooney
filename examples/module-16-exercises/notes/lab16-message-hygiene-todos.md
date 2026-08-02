# Lab 16 — Fill Message Hygiene TODOs

## Step 1 — Copy TODOs

Safe not-found message: Customer was not found
Unsafe message anti-pattern: Customer CUS-#### was not found (this could expose sensitive information)
Correlation always field: correlationId
Log stack trace? yes (server logs yes)
Return stack trace to client? no
@ControllerAdvice live in this pre-lab? no

## Step 2 — Fill blanks

Fill safe message for unknown customer, unsafe SQL/PII example, `correlationId`, yes for server logs, no for client, no for live advice.

## Step 3 — Correlation always

 *Every error sketch includes lab-request-001 (or request header value).*

## Step 4 — Self-check

Confirm client stack-trace blank is no.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.