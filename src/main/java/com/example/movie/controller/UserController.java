package com.example.movie.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/pass")
    public void changePassword(@RequestBody String password) {
        userService.changePassword(password);
    }

    @PatchMapping("/name")
    public void changeUsername(@RequestBody String username) {
        userService.changeUsername(username);
    }

    @GetMapping("/type")
    public Optional<String> getUserType(@RequestBody String username) {
        return userService.getUserType(username);
    }

    @GetMapping("/")
    public Optional<User> getUserById(@RequestBody String idStr) {
        Long id = Long.parseLong(idStr.trim());
        return userService.getUserById(id);
    }
}

