package dev.rahulrajesh.PicPayData.controllers;

import dev.rahulrajesh.PicPayData.model.User;
import dev.rahulrajesh.PicPayData.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
