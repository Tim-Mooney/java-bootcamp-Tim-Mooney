Lab 16 — Failure to Status Map

## Reference

| Failure | Status idea             |
| --- |-------------------------|
| CUS-9999 not found | 404 / SOAP Client fault |
| Activate Amina illegal transition | 409                     |
| Validation blank name | 400                     |
| Unexpected bug | 500 (generic message)   |

## Step 2 — Choose conflict

Pick 409 vs 422 for illegal activate and write one reason.
I picked 409 because it is a business conflict, not a validation error. 
The request is syntactically correct, but the state of the resource does not allow the requested operation.

## Step 3 — Never

never return 200 with an error payload for these failures.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.