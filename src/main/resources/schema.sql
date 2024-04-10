CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(254) UNIQUE NOT NULL,
    name VARCHAR(250) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    title VARCHAR(255) NOT NULL,
    CONSTRAINT unique_category_user UNIQUE (user_id, title)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    title VARCHAR(50) NOT NULL,
    balance FLOAT,
    CONSTRAINT unique_account_user UNIQUE (user_id, title)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    category_id BIGINT REFERENCES categories(id),
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    description VARCHAR(1000),
    amount FLOAT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS transfers (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    source_account_id BIGINT NOT NULL REFERENCES accounts(id),
    destination_account_id BIGINT NOT NULL REFERENCES accounts(id),
    amount FLOAT NOT NULL,
    description VARCHAR(1000),
    transfer_date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);