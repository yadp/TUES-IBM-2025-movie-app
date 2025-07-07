package com.example.movie.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void testUser() {
        User user = new User("Petur", "petur@petur", "pass123", "simple");
        assertEquals("Petur", user.getUsername());
        assertEquals("petur@petur", user.getEmail());
        assertEquals("pass123", user.getPassword());
        assertEquals("simple", user.getType());
    }
}
