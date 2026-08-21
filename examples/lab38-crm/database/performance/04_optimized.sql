-- TODO: replace DATE_TRUNC/TRUNC(created_at) filters with half-open tstz range
-- TODO: keyset page: WHERE (created_at, customer_id) < ($ts, $id) ORDER BY ... LIMIT 50
-- TODO: compare nested loop vs hash join hints/plans for customer→account

SELECT customer_id, email, status, created_at
FROM customer
WHERE created_at >= date_trunc('day', now())::timestamptz
  AND created_at <  date_trunc('day', now())::timestamptz + interval '1 day'
  LIMIT 20;

SELECT customer_id, email, status, created_at
FROM customer
WHERE status = 'ACTIVE'
ORDER BY created_at DESC, customer_id DESC
    LIMIT 50;

WITH cursor AS (
    SELECT created_at, customer_id
    FROM customer
    WHERE status = 'ACTIVE'
    ORDER BY created_at DESC, customer_id DESC
    LIMIT 1 OFFSET 49
    )
SELECT customer.customer_id, customer.email, customer.status, customer.created_at
FROM customer, cursor
WHERE customer.status = 'ACTIVE'
  AND (customer.created_at, customer.customer_id) < (cursor.created_at, cursor.customer_id)
ORDER BY customer.created_at DESC, customer.customer_id DESC
    LIMIT 50;

-- Compare nested loop vs hash join for customer→account
-- Baseline plan (let planner decide):
EXPLAIN (ANALYZE, BUFFERS)
SELECT c.customer_id, c.email, a.account_number
FROM customer c
         JOIN account a ON a.customer_id = c.customer_id
WHERE c.customer_id = 1001;

-- Force nested loop for comparison (wrapped in a transaction so SET LOCAL is valid):
BEGIN;
SET LOCAL enable_hashjoin = off;
EXPLAIN (ANALYZE, BUFFERS)
SELECT c.customer_id, c.email, a.account_number
FROM customer c
         JOIN account a ON a.customer_id = c.customer_id
WHERE c.customer_id = 1001;
COMMIT;