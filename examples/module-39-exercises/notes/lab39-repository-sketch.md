# Lab 39 — Repository Sketch

## Step 1 — CustomerRepository

`findById`, `findByStatus`, `findAll(Pageable)`.

## Step 2 — AccountRepository

`findByCustomerId(String customerId)` for Amina/Ravi.

## Step 3 — Derived vs @Query

Note when a `@Query` might be clearer than a long derived name.
If the name is something long and specific, like "people whose name starts with A and were born before 2002 but after 2000",
 it's easier just make an @Query

## Step 4 — Service boundary

Controllers talk to services; services use repositories.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.