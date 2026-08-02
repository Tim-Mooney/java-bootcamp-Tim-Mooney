# Lab 16 — Correlation on Every Error

## Step 1 — Success path

Activate Ravi success still echoes/logs lab-request-001.
activateCustomer(Ravi) success -> correlationId: lab-request-001

## Step 2 — Failure path

Not-found CUS-9999 response includes same correlation field.
notFound(CUS-9999) failure -> correlationId: lab-request-001

## Step 3 — Missing header

Policy idea: generate a correlation if missing — note for later labs.
header missing policy -> generate a correlationId

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.