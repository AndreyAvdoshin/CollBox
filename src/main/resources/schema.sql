CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(254) UNIQUE NOT NULL,
    name VARCHAR(250) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salary_day INTEGER DEFAULT NULL,
    role VARCHAR(255) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    title VARCHAR(255) NOT NULL,
    category_type VARCHAR(20),
    CONSTRAINT unique_category_user UNIQUE (user_id, title)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    title VARCHAR(50) NOT NULL,
    balance NUMERIC(10, 2) DEFAULT 0.00,
    CONSTRAINT unique_account_user UNIQUE (user_id, title)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    category_id BIGINT REFERENCES categories(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    description VARCHAR(1000),
    amount NUMERIC(10, 2) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transfers (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    source_account_id BIGINT NOT NULL REFERENCES accounts(id),
    destination_account_id BIGINT NOT NULL REFERENCES accounts(id),
    amount NUMERIC(10, 2) NOT NULL,
    description VARCHAR(1000),
    transfer_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION set_updated() RETURNS trigger
AS
'
BEGIN
  NEW.updated = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER update_timestamp_to_update BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE set_updated();

CREATE OR REPLACE TRIGGER update_timestamp_to_update BEFORE UPDATE ON transfers
FOR EACH ROW
EXECUTE PROCEDURE set_updated();

CREATE OR REPLACE TRIGGER update_timestamp_to_update BEFORE UPDATE ON transactions
FOR EACH ROW
EXECUTE PROCEDURE set_updated();