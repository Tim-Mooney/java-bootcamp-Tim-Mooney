# Lab 18 — When to Keep Real Validator

## Step 1 — Mock repo

Mock CustomerRepository — I/O boundary.

## Step 2 — Real validator?

Keep a pure StatusValidator real if it is deterministic and fast.

## Step 3 — Mock notifier

Mock notifier to avoid email/IO in unit tests.

## Step 4 — Rule

Write: mock I/O and unstable deps; keep pure domain helpers real when cheap.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.