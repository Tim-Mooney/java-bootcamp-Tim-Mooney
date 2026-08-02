# Lab 16 — Catch Order

## Step 1 — List types

NotFoundException, ConflictException, ValidationException, Exception.

## Step 2 — Order

Write the catch/handler order top-to-bottom specific → general.
try{
    // code that may throw exceptions
} catch (NotFoundException e) {
    // handle NotFoundException
} catch (ConflictException e) {
    // handle ConflictException
} catch (ValidationException e) {
    // handle ValidationException
} catch (Exception e) {
    // handle any other exceptions
}

## Step 3 — Why

broad catch first would shadow domain mapping.

## Step 4 — Prep only

Write: *Do not complete full Lab 16 advice wiring in pre-lab.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.