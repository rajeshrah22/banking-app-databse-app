-- Wallet Table
CREATE TABLE Wallet (
    id int auto_increment,
    user int,
    balance double,
    createdOn timestamp,
    lastTransactedOn timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES User (id)
);

-- User Table
CREATE TABLE User(
    id int auto_increment,
    version int,
    fName VARCHAR(30),
    lName VARCHAR(30),
    walletID int,
    PRIMARY KEY (id),
    FOREIGN KEY (walletID) REFERENCES Wallet(id)
);