
CREATE TABLE IF NOT EXISTS customer (
    customer_id           BIGSERIAL PRIMARY KEY,
    public_id             VARCHAR(32) NOT NULL,
    full_name             VARCHAR(200) NOT NULL,
    email                 VARCHAR(320) NOT NULL,
    status                VARCHAR(32) NOT NULL,
    created_at            TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at            TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uk_customer_public UNIQUE (public_id),
    CONSTRAINT uk_customer_email UNIQUE (email),
    CONSTRAINT ck_customer_status CHECK (
     status IN ('PROSPECT', 'ACTIVE', 'CLOSED'))
    );

CREATE TABLE IF NOT EXISTS account (
    account_id            BIGSERIAL PRIMARY KEY,
    customer_id           BIGINT NOT NULL,
    account_number        VARCHAR(32) NOT NULL,
    balance_cents         BIGINT NOT NULL DEFAULT 0,
    opened_at             TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uk_account_number UNIQUE (account_number),
    CONSTRAINT fk_account_customer FOREIGN KEY (customer_id)
    REFERENCES customer (customer_id)
    );

-- indexes supporting email lookup and status filters

CREATE INDEX idx_customer_email ON customer (email);
CREATE INDEX idx_customer_status ON customer (status);
