package com.example.movie.service;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.exception.*;
import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HttpSession session;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public ResponseEntity<String> signup(User u) throws UserExistsException {
        if (isLoggedIn()) {
            throw new UserAlreadyLoggedInException("User already logged in");
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (userRepo.findByUsername(u.getUsername()) != null) {
            throw new UserExistsException("User already exists");
        }

        User toSave = new User(u.getUsername(), u.getEmail(), encoder.encode(u.getPassword()), u.getType());

        session.setAttribute("userId", u.getId());
        session.setAttribute("username", u.getUsername());
        session.setMaxInactiveInterval(48 * 3600);

        createUser(toSave);
        return ResponseEntity.ok("Sign Up successful");
    }

    public ResponseEntity<String> login(User u)
            throws UserNotFoundException, UserAlreadyLoggedInException, IncorrectPasswordException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (isLoggedIn()) {
            throw new UserAlreadyLoggedInException("User already logged in");
        }

        User dbUser = userRepo.findByUsername(u.getUsername());
        if (dbUser == null) {
            throw new UserNotFoundException("No user with that username found");
        }

        boolean check = encoder.matches(u.getPassword(), dbUser.getPassword());


        if (!check) {
            throw new IncorrectPasswordException("Incorrect password");
        } else {
            session.setAttribute("userId", dbUser.getId());
            session.setAttribute("username", dbUser.getUsername());
            session.setMaxInactiveInterval(48 * 3600);

            return ResponseEntity.ok("Login successful");
        }
    }

    public ResponseEntity<String> logout() throws UserAlreadyLoggedOutException {
        if (!isLoggedIn()) {
            throw new UserAlreadyLoggedOutException("User already logged out");
        }

        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    public boolean isLoggedIn() {
        return session.getAttribute("userId") != null;
    }

    public Optional<User> getCurrentUser() {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return Optional.empty();
        }

        return userRepo.findById(userId);
    }

    public void changePassword(String newPassword) throws UserAlreadyLoggedOutException {
        Object userId = session.getAttribute("userId");
        Object username = session.getAttribute("username");

        if (userId == null || username == null) {
            throw new UserAlreadyLoggedOutException("User logged out");
        }

        User user = userRepo.findByUsername(String.valueOf(username));

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);

        user.setPassword(encodedPassword);
        userRepo.save(user);
    }

    public void changeUsername(String newUsername) throws UserAlreadyLoggedOutException {
        Object userId = session.getAttribute("userId");
        Object username = session.getAttribute("username");

        if (userId == null || username == null) {
            throw new UserAlreadyLoggedOutException("User logged out");
        }

        User user = userRepo.findByUsername(String.valueOf(username));

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setUsername(newUsername);
        userRepo.save(user);
        logout();
    }
}