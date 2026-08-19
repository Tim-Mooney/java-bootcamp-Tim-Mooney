# Lab 40 — Fill SAST Path TODOs

Endpoint: /api/customers/{id} Authz check: {id} Sink (SQL/file/log): SELECT FROM customers WHERE id IS CUS-1001 Customer fixture used: CUS-1001 Risk if missing check: anyone can view Amina's details


## Step 2 — Fill for customer read

Fill blanks for `GET /api/customers/{id}` using `CUS-1001`. Authz must mention role/object-level check TODOs.

## Step 3 — Second path

Duplicate the template for a write path (update interaction or status) involving `CUS-1002`.
Endpoint: /api/customers/{id}/status Authz check: {id} Sink (SQL/file/log): SELECT FROM customers WHERE id IS CUS-1002 Customer fixture used: CUS-1002 Risk if missing check: anyone can change Ravi's status


## Scope
Pre-lab only — do not finish the full graded lab in this exercise.