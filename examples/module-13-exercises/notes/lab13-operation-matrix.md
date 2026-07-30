# Lab 13 — Operation Matrix

## Step 1 — GetCustomer

In: customerId; Out: id, name, status; Fault: not found.
so send a request with CUS-1001 in payload
receive a response with CUS-1001, Amina Khan, ACTIVE

## Step 2 — ActivateCustomer

In: customerId (+ correlation header idea); Out: new status; Fault: invalid transition.

## Step 3 — Happy path

Note Activate on CUS-1002 Ravi PROSPECT → ACTIVE as the design happy path.

## Step 4 — Prep only

Write: *Design only — do not complete full Lab 13 build.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.