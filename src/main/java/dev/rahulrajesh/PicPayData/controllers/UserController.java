package dev.rahulrajesh.PicPayData.controllers;

import dev.rahulrajesh.PicPayData.model.Login;
import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.services.LoginService;
import dev.rahulrajesh.PicPayData.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

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

    @PostMapping("/transaction/{fromCpf}/{toCpf}/{amount}")
    public ResponseEntity<String> transactAmount
            (@PathVariable long fromCpf, @PathVariable long toCpf, @PathVariable double amount) {
        String result = "";
        try {
            result = userService.transactAmount(fromCpf, toCpf, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User resultUser = userService.createUser(user);

        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login resultLogin = loginService.createLoginJDBCTemplate(login);

        return new ResponseEntity<>(resultLogin, HttpStatus.OK);
    }

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

    @PostMapping("/deleteLogin")
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

}
