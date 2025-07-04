package com.example.movie.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.exception.UserExistsException;
import com.example.movie.exception.UserNotFoundException;
import com.example.movie.model.User;
import com.example.movie.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public void createUser(@RequestBody User user) throws UserExistsException {
        userService.signup(user);
    }

    @PostMapping("/login")
    public void logUser(@RequestBody User user) throws UserNotFoundException {
        userService.login(user);
    }

    @PostMapping("/logout")
    public void logOutUser() throws UserAlreadyLoggedOutException {
        userService.logout();
    }

    @PostMapping("/pass")
    public void changePassword(@RequestBody String password) {
        userService.changePassword(password);
    }

    @GetMapping("/")
    public Optional<User> getUserById(@RequestBody Long id) {
        return userService.getUserById(id);
    }
}

