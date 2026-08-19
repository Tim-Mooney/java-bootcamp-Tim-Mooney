-- TODO: generate >= 50k customers with skewed status distribution
-- Keep fixture public_id CUS-1001 / CUS-1002 intact if already seeded
-- Prefer set-based INSERT...SELECT generate_series(...)
BEGIN;      --this ensures that it either all happens or none of it happens (atomic)

INSERT INTO customer (public_id, full_name, email, status)
VALUES ('CUS-1001', 'Amina Khan', 'amina@example.com', 'ACTIVE');

INSERT INTO customer (public_id, full_name, email, status)
VALUES ('CUS-1002', 'Ravi Singh', 'ravi@example.com',  'PROSPECT');

INSERT INTO customers (public_id, name, email, status)
SELECT
    'CUS-' || n::text                                  AS public_id,
    'cust' || n::text                                  AS name,
    'cust' || n::text || '@email.com'                  AS email,
    CASE
        WHEN rand < .75 THEN 'ACTIVE'       --75% active
        WHEN rand < .95 THEN 'PROSPECT'     --20% prospect
        ELSE 'CLOSED'                       --5% closed
    END
FROM {
    SELECT n, random() as r,
    FROM generate_series(1003, 51000) AS n
    } gen

COMMIT;