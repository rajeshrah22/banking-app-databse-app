-- Wallet Table
CREATE TABLE Wallet (
    id int auto_increment,
    balance double,
    createdOn timestamp,
    lastTransactedOn timestamp,
    PRIMARY KEY (id)
);

-- User Table
CREATE TABLE User(
    id int auto_increment,
    fName VARCHAR(30),
    lName VARCHAR(30),
    walletID int,
    PRIMARY KEY (id),
    FOREIGN KEY (walletID) REFERENCES Wallet(id)
);