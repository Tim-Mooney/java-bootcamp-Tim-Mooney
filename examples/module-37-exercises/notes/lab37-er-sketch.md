# Lab 37 — ER Sketch

## Reference

| Relationship | Cardinality |
| --- | --- |
| customer → account | 1:N |
| account.customer_id | FK → customer.customer_id |
| customer.customer_id | PK / unique business key |

## Step 2 — Diagram

CUSTOMER ||--o{ ACCOUNT : owns
CUSTOMER {
string customer_id PK
}
ACCOUNT {
string account_id PK
string customer_id FK
}

## Step 3 — Cascade policy

Decide ON DELETE behavior (RESTRICT vs CASCADE) and justify.
Restrict because that's default

## Step 4 — Boundary

Do not create Kafka outbox tables in this module unless guide requires.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.