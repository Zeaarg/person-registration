package com.example.personRegistration.controller;

import com.example.personRegistration.model.User;
import com.example.personRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity saveUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
