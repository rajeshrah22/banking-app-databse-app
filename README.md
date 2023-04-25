# PicPayData

PicPayData is a Spring Boot REST API that manages user data and login credentials. The API includes endpoints for creating and deleting users and logins, retrieving user and wallet information, adjusting wallet balances, and performing transactions between users within a MySQL databse.

## Technologies Used

- Java 8
- Spring Boot 3.0.3
- MySQL 8.0.32
- Maven 3.9.0

## Getting Started

1. Clone the repository to your local machine.
2. Install Java 11 and Maven.
3. Configure your MySQL database connection in `application.properties`.
4. Run `mvn clean install` to build the project.
5. Run `mvn spring-boot:run` to start the server.

## Endpoints

- `GET /user/{cpf}`: retrieves a user by CPF.
- `GET /user/byEmail`: retrieves a user by email.
- `GET /userWallet/{cpf}`: retrieves a user's wallet by CPF.
- `POST /adjustAmount/{cpf}/{amount}`: adjusts a user's wallet balance by a specified amount.
- `POST /transaction`: performs a transaction between two users.
- `POST /user`: creates a new user.
- `POST /login`: creates a new login.
- `GET /login`: retrieves a login by username.
- `POST /login/deleteLogin`: deletes a login by username.
- `GET /user/usernameContains/{numResults}`: retrieves a list of users whose names contain a specified substring.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
