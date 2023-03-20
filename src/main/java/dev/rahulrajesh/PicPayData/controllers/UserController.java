package dev.rahulrajesh.PicPayData.controllers;

import dev.rahulrajesh.PicPayData.model.Login;
import dev.rahulrajesh.PicPayData.model.Transaction;
import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.services.LoginService;
import dev.rahulrajesh.PicPayData.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This Spring Boot REST API controller manages user data and login credentials.
 * It provides endpoints for CRUD operations on users and logins.
 * Dependencies on UserService and LoginService handle business logic and interact with a MySQL database.
 * HTTP status codes indicate the success or failure of each request.
 */
@RestController
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    /**
     * Retrieves a User object from the database by email.
     * Returns a ResponseEntity containing the User object and a status code of OK (200).
     */
    @GetMapping("/user/{cpf}")
    public ResponseEntity<User> getUserByCpf(@PathVariable long cpf) {
        User resultUser = null;
        try {
            resultUser = userService.getUserByCpf(cpf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    /**
     * Retrieves a User object from the database by email.
     * Returns a ResponseEntity containing the User object and a status code of OK (200).
     */
    @GetMapping("/user/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User resultUser = null;
        try {
            resultUser = userService.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    /**
     * Retrieves a Wallet object for a given user by CPF.
     * Returns a ResponseEntity containing the Wallet object and a status code of OK (200).
     */
    @GetMapping("/userWallet/{cpf}")
    public ResponseEntity<Wallet> getUserWalletByCpf(@PathVariable long cpf) {
       Wallet resultWallet = null;
       try {
           resultWallet = userService.getWalletByUserCpf(cpf);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return new ResponseEntity<>(resultWallet, HttpStatus.OK);
    }

    /**
     * Adjusts the amount in a user's wallet by a specified amount.
     * Returns a ResponseEntity containing the updated Wallet object and a status code of OK (200).
     */
    @PostMapping("/adjustAmount/{cpf}/{amount}")
    public ResponseEntity<Wallet> adjustAmountByUserCpf(@PathVariable long cpf, @PathVariable double amount) {
        Wallet resultWallet = null;

        try{
            resultWallet = userService.adjustAmountByUserCpf(cpf, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultWallet, HttpStatus.OK);
    }

    /**
     * Performs a transaction between two users' wallets.
     * Returns a ResponseEntity containing the result string ("Success" or an error message) and a status code of OK (200).
     */
    @PostMapping("/transaction")
    public ResponseEntity<String> transactAmount
            (@RequestBody Transaction transaction) {
        String result = "";
        try {
            result = userService
                    .transactAmount
                            (transaction.getPayer(), transaction.getPayee(), transaction.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Creates a new User object.
     * Returns a ResponseEntity containing the newly created User object and a status code of OK (200).
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User resultUser = userService.createUser(user);

        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    /**
     * Creates a new Login object.
     * Returns a ResponseEntity containing the newly created Login object and a status code of OK (200).
     */
    @PostMapping("/login")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login resultLogin = loginService.createLoginJDBCTemplate(login);

        return new ResponseEntity<>(resultLogin, HttpStatus.OK);
    }

    /**
     * Retrieves a Login object from the database by username.
     * Returns a ResponseEntity containing the Login object and a status code of OK (200).
     */
    @GetMapping("/login")
    public ResponseEntity<Login> getLogin(@RequestParam String username) {
        ResponseEntity<Login> re;
        Login resultLogin = null;

        try {
            resultLogin = loginService.getLogin(username);
        } catch (NoSuchElementException e) {
            re = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        re = new ResponseEntity<>(resultLogin, HttpStatus.OK);

        return re;
    }

    /**
     * Deletes a Login object from the database by username.
     * Returns a ResponseEntity containing the deleted Login object and a status code of OK (200).
     * If no such Login object exists, returns a ResponseEntity with a null body and a status code of BAD_REQUEST (400).
     */
    @PostMapping("/login/deleteLogin")
    public ResponseEntity<Login> deleteLogin(@RequestParam String username) {
        ResponseEntity<Login> re;
        Login resultLogin = null;

        try {
            resultLogin = loginService.deleteLoginJDBCTemplate(username);
        } catch (NoSuchElementException e) {
            re = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        re = new ResponseEntity<>(resultLogin, HttpStatus.OK);

        return re;
    }

    /**
     * Retrieves a list of User objects whose names contain the specified substring, up to a specified number of results.
     * Returns a ResponseEntity containing the list of User objects and a status code of OK (200).
     */
    @GetMapping("/user/usernameContains/{numResults}")
    public ResponseEntity<List<User>>
            getUsersWithNameContaining(@RequestParam String substring, @PathVariable int numResults) {
        System.out.println(substring);

        List<User> userList = userService.getUsersWithNameContaining(substring, numResults);

        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

}
