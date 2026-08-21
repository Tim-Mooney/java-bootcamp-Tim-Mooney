-- EXPLAIN (ANALYZE, BUFFERS) email lookup for a known address
EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id, email, status, created_at
FROM customer
WHERE email = 'ravi@example.com';

-- EXPLAIN list ACTIVE customers ORDER BY created_at, customer_id LIMIT 50 OFFSET 0
EXPLAIN (ANALYZE, BUFFERS)
SELECT customer_id, email, status, created_at
FROM customer
WHERE status = 'ACTIVE'
ORDER BY created_at, customer_id
    LIMIT 50 OFFSET 0;