# Lab 35 — Endpoint Map

## Reference

| UI action       | HTTP                         |
|-----------------|------------------------------|
| List customers  | GET /api/customers           |
| Open Amina      | GET /api/customers/CUS-1001  |
| Create customer | POST /api/customers          |
| Update status   | PATCH /api/customers/{id}    |
| Open Ravi       | GET /api/customers/CUS-1002  |

## Step 2 — Ravi row

Add GET for `CUS-1002`.

## Step 3 — Status codes

List expected codes: 200, 201, 400, 404, 500.

## Step 4 — JSON shape

Sketch list item JSON: customerId, name, status.
{
"customerId": "CUS-1001",
"name": "Amina Khan",
"status": "ACTIVE"
}

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.