# Lab 12 — Smell Bingo

## Step 1 — Smell list

Bingo card: long method, magic strings for ACTIVE/PROSPECT, == on Strings, mixed I/O in domain, unclear names.

## Step 2 — Fixture tie-in

For each smell, note how it could corrupt CUS-1001 / CUS-1002 handling.
long method: hard to test, could be correct overall but could make development difficult
magic strings: could misspell ACTIVE or PROSPECT. Could cause mislabeling.
**== on Strings: could lead to unexpected behavior if strings are not interned.**
**mixed I/O in domain: could make the domain logic harder to test and maintain.**
unclear names: could make the code harder to understand and maintain.

## Step 3 — Priority

Star the two smells you will fix first in the timed lab.
== on strings and mixed I/O in domain.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.