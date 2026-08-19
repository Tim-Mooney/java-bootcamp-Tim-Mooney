# Lab 38 — Access Patterns

## Step 1 — Patterns

By customer_id (`CUS-1001`), by status, by created_at range, accounts by customer_id.

## Step 2 — Hot path

Mark lookup by customer_id as the hottest path.
SELECT customer_id, full_name, status FROM customer WHERE customer_id = 'CUS-1001';

## Step 3 — Anti-pattern

`SELECT *` without WHERE on huge tables — avoid in app code.


