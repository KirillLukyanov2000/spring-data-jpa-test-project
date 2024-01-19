CREATE SCHEMA IF NOT EXISTS customer;
drop table if exists customer.customer;
CREATE TABLE IF NOT EXISTS customer
(
    id       BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO customer(id, login, password) VALUE (1, 'Vasya', 'pass1'), (2, 'Dima', 'pass2');

