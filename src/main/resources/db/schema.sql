DROP INDEX IF EXISTS IDX_ACCOUNT_SURNAME;
DROP TABLE IF EXISTS accounts;
DROP SEQUENCE IF EXISTS sq_accounts;

CREATE SEQUENCE sq_accounts START WITH 1 INCREMENT BY 10;

CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IDX_ACCOUNT_SURNAME ON accounts (surname);
