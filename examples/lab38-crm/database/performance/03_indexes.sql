CREATE INDEX CONCURRENTLY idx_customers_status_created_id
    ON customer (status, created_at, customer_id);

CREATE INDEX idx_customer_email ON customer (email);
CREATE INDEX idx_customer_status ON customer (status);