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
    balance double NOT NULL DEFAULT 0,
    created timestamp,
    txdate timestamp,
    user bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES User(cpf)
);

CREATE TABLE Login (
    user bigint,
    password VARCHAR(30),
    username VARCHAR(30) PRIMARY KEY
);

