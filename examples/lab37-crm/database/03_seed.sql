-- TODO: insert CUS-1001 Amina ACTIVE + one account
-- TODO: insert CUS-1002 Ravi PROSPECT (no account)
-- Prefer public_id values CUS-1001 / CUS-1002

INSERT INTO customer (public_id, full_name, email, status)
VALUES ('CUS-1001', 'Amina Khan', 'amina@example.com', 'ACTIVE');

INSERT INTO customer (public_id, full_name, email, status)
VALUES ('CUS-1002', 'Ravi Singh', 'ravi@example.com',  'PROSPECT');

INSERT INTO account (account_number, customer_id, balance_cents)
SELECT 'ACCT-1001-01', customer_id,  250000
FROM customer WHERE public_id = 'CUS-1001';

INSERT INTO customer (public_id, full_name, email, status)
VALUES ('CUS-1003', 'BAD CUSTOMER', 'ravi@example.com',  'PROSPECT');