# Lab 17 — Meaningful Asserts

## Step 1 — Weak

`assertNotNull(result)` this is weak

## Step 2 — Strong

assertEquals("CUS-1002", result.getCustomerId()) this is strong
assertEquals(CustomerStatus.ACTIVE, result.getStatus()) this is strong

## Step 3 — Exception assert

Plan `assertThrows` for activating Amina under your illegal policy.
ACTIVE -> ACTIVE
assertThrows(IllegalStateException.class, () -> service.activateCustomer("CUS-1001"));

## Step 4 — Prep only

Write: *Prepare for Lab 17; do not complete full suite now.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.