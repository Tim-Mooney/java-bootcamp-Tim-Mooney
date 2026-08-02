# Lab 17 — Expressive Test Names

## Step 1 — Pattern

Use methodName_state_expectedOutcome style.

## Step 2 — Examples

Write names for Amina already ACTIVE reject, Ravi PROSPECT activate success, CUS-9999 not found.
activateCustomer_alreadyActive_reject()
activateCustomer_prospect_activateSuccess()
activateCustomer_notFound_reject()

## Step 3 — Anti-name

Reject names like `test1` / `testActivate`.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.