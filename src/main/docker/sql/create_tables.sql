CREATE TABLE accounts (
    accountId SERIAL PRIMARY KEY,
    customerId INT NOT NULL
);

CREATE TABLE balances (
    accountId INT NOT NULL,
    currencyId INT NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    CONSTRAINT balances_fk FOREIGN KEY (accountId) REFERENCES accounts(accountId)
);

-- Table: currencies
CREATE TABLE currencies (
    currencyId int  NOT NULL,
    currencyType varchar(6)  NOT NULL,
    CONSTRAINT currencies_pk PRIMARY KEY (currencyId)
);

-- Table: customers
CREATE TABLE customers (
    customerId int  NOT NULL,
    country varchar(20)  NOT NULL,
    CONSTRAINT customers_pk PRIMARY KEY (customerId)
);

-- Table: transactionType
CREATE TABLE transactionType (
    transactionTypeId int  NOT NULL,
    transactionType varchar(10)  NOT NULL,
    CONSTRAINT transactionType_pk PRIMARY KEY (transactionTypeId)
);

-- Table: transactions
CREATE TABLE transactions (
    transactionId int  NOT NULL,
    accountId int  NOT NULL,
    currencyId int  NOT NULL,
    transactionType int  NOT NULL,
    amount money  NOT NULL,
    description varchar(150)  NOT NULL,
    CONSTRAINT transactions_pk PRIMARY KEY (transactionId)
);