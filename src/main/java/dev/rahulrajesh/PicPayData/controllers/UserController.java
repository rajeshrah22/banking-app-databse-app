package dev.rahulrajesh.PicPayData.controllers;

import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{cpf}")
    public ResponseEntity<User> getUserByCpf(@PathVariable long cpf) {
        User resultUser = null;
        try {
            resultUser = userService.getUserByCpf(cpf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<User>(resultUser, HttpStatus.OK);
    }

    @GetMapping("/userWallet/{cpf}")
    public ResponseEntity<Wallet> getUserWalletByCpf(@PathVariable long cpf) {
       Wallet resultWallet = null;
       try {
           resultWallet = userService.getWalletByUserCpf(cpf);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return new ResponseEntity<Wallet>(resultWallet, HttpStatus.OK);
    }

    @PutMapping("/adjustAmount/{cpf}/{amount}")
    public ResponseEntity<Wallet> adjustAmountByUserCpf(@PathVariable long cpf, @PathVariable double amount) {
        Wallet resultWallet = null;

        try{
            resultWallet = userService.adjustAmountByUserCpf(cpf, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Wallet>(resultWallet, HttpStatus.OK);
    }
}
