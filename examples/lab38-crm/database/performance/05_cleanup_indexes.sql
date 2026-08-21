BEGIN;
DROP INDEX idx_customers_status_created_id;

EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id, email, status, created_at
FROM customer
WHERE status = 'ACTIVE'
ORDER BY created_at, customer_id
    LIMIT 50 OFFSET 0;


ROLLBACK;


BEGIN;
DROP INDEX idx_customer_email;

EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id, email, status, created_at
FROM customer
WHERE email = 'cus1001@example.com';

ROLLBACK;