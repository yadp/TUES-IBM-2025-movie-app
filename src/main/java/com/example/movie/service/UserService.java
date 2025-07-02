package com.example.movie.service;

import com.example.movie.exception.UserExistsException;
import com.example.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.movie.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public ResponseEntity<String> signup(User u) throws UserExistsException {
        if (userRepo.findByUsername(u.getUsername()) != null) {
            throw new UserExistsException("User already exists");
        }

        createUser(u);
        return ResponseEntity.ok("Login successful");
    }
}
