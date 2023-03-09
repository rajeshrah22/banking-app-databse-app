-- User Table
CREATE TABLE User(
    cpf bigint auto_increment,
    fname VARCHAR(30),
    lname VARCHAR(30),
    email VARCHAR(30),
    usertype VARCHAR(15),
    PRIMARY KEY (cpf)
);

-- Wallet Table
CREATE TABLE Wallet (
    id int auto_increment,
    balance double,
    created timestamp,
    txdate timestamp,
    user bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES User(cpf)
);

describe wallet;


