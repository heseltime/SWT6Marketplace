/* -- Create schema if it does not exist
create schema if not exists MARKETPLACE;

-- Set the schema for the session
set schema MARKETPLACE;

-- Create tables if they do not already exist
create table if not exists ARTICLE
(
    ARTICLE_ID         INTEGER generated always as identity
        primary key,
    NAME               VARCHAR(255)   not null,
    SHORT_DESCRIPTION  VARCHAR(500)   not null,
    PRICE              DECIMAL(10, 2) not null,
    STOCK_AMOUNT       INTEGER,
    SHIPMENT_COUNTRIES VARCHAR(255),
    SHIPMENT_COST      DECIMAL(10, 2),
    RATING             INTEGER
);

create table if not exists CUSTOMER
(
    CUSTOMER_ID      INTEGER generated always as identity
        primary key,
    NAME             VARCHAR(255) not null,
    BILLING_ADDRESS  VARCHAR(255) not null,
    EMAIL            VARCHAR(255) not null,
    SHIPMENT_ADDRESS VARCHAR(255)
);

create table if not exists CART
(
    CART_ID     INTEGER generated always as identity
        primary key,
    CUSTOMER_ID INTEGER
        constraint FK_CART_CUSTOMER_ID
        references CUSTOMER
);

create table if not exists ARTICLE_CART
(
    ID         INTEGER generated always as identity
        primary key,
    ARTICLE_ID INTEGER
        constraint FK_ARTICLE_ID
        references ARTICLE,
    CART_ID    INTEGER
        constraint FK_CART_ID
        references CART,
    AMOUNT     INTEGER
);

create table if not exists PAYMENTMETHOD
(
    PM_ID           INTEGER generated always as identity
        primary key,
    PROVIDER        VARCHAR(50),
    USER_IDENTIFIER VARCHAR(50),
    CARD_NUMBER     VARCHAR(16),
    IBAN            VARCHAR(34),
    CHECK_DIGIT     VARCHAR(5),
    CUSTOMER_ID     INTEGER
        constraint FK_CUSTOMER_ID
        references CUSTOMER
);

create table if not exists RETAILER
(
    RETAILER_ID INTEGER generated always as identity
        primary key,
    NAME        VARCHAR(255) not null,
    ADDRESS     VARCHAR(255) not null
);

create table if not exists DISCOUNTCODE
(
    CODE_ID     INTEGER generated always as identity
        primary key,
    RETAILER_ID INTEGER
        constraint FK_DISCOUNTCODE_RETAILER_ID
        references RETAILER,
    CODE        VARCHAR(20),
    VALID_FROM  DATE,
    VALID_TO    DATE
);

create table if not exists RETAILER_ARTICLE
(
    ID          INTEGER generated always as identity
        primary key,
    RETAILER_ID INTEGER
        constraint FK_RETAILER_ID
        references RETAILER,
    ARTICLE_ID  INTEGER
        constraint FK_ARTICLE_RETAILER_ID
        references ARTICLE
);


-- Add dummy content for all tables ...
-- Insert dummy data into ARTICLE
insert into ARTICLE (NAME, SHORT_DESCRIPTION, PRICE, STOCK_AMOUNT, SHIPMENT_COUNTRIES, SHIPMENT_COST, RATING) values
    ('Article 1', 'Short description for article 1', 19.99, 100, 'USA, Canada', 5.00, 4),
    ('Article 2', 'Short description for article 2', 29.99, 200, 'USA, UK', 7.50, 5),
    ('Article 3', 'Short description for article 3', 39.99, 150, 'Canada, UK', 6.00, 3);

-- Insert dummy data into CUSTOMER
insert into CUSTOMER (NAME, BILLING_ADDRESS, EMAIL, SHIPMENT_ADDRESS) values
    ('John Doe', '123 Main St, Anytown, USA', 'johndoe@example.com', '123 Main St, Anytown, USA'),
    ('Jane Smith', '456 Elm St, Othertown, USA', 'janesmith@example.com', '456 Elm St, Othertown, USA'),
    ('Alice Johnson', '789 Oak St, Anycity, USA', 'alicejohnson@example.com', '789 Oak St, Anycity, USA');

-- Insert dummy data into CART
insert into CART (CUSTOMER_ID) values
   (1),
   (2),
   (3);

-- Insert dummy data into ARTICLE_CART
insert into ARTICLE_CART (ARTICLE_ID, CART_ID, AMOUNT) values
   (1, 1, 2),
   (2, 1, 1),
   (3, 2, 1),
   (1, 3, 3);

-- Insert dummy data into PAYMENTMETHOD
insert into PAYMENTMETHOD (PROVIDER, USER_IDENTIFIER, CARD_NUMBER, IBAN, CHECK_DIGIT, CUSTOMER_ID) values
   ('Visa', 'john_doe', '4111111111111111', 'DE89370400440532013000', '12345', 1),
   ('MasterCard', 'jane_smith', '5500000000000004', 'DE89370400440532013001', '67890', 2),
   ('PayPal', 'alice_johnson', null, 'DE89370400440532013002', null, 3);

-- Insert dummy data into RETAILER
insert into RETAILER (NAME, ADDRESS) values
    ('Retailer 1', '101 Market St, Somecity, USA'),
    ('Retailer 2', '202 Commerce St, Anothertown, USA');

-- Insert dummy data into DISCOUNTCODE
insert into DISCOUNTCODE (RETAILER_ID, CODE, VALID_FROM, VALID_TO) values
   (1, 'DISCOUNT10', '2024-01-01', '2024-12-31'),
   (2, 'SAVE20', '2024-01-01', '2024-12-31');

-- Insert dummy data into RETAILER_ARTICLE
insert into RETAILER_ARTICLE (RETAILER_ID, ARTICLE_ID) values
    (1, 1),
    (1, 2),
    (2, 3);*/
