package com.example.movie.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private static final String USER_NAME = "Petur";
    private static final String USER_EMAIL = "petur@petur";
    private static final String USER_PASSWORD = "pass123";
    private static final String USER_ROLE = "simple";

    @Test
    void testUser() {
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_ROLE);
        assertEquals(USER_NAME, user.getUsername());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_PASSWORD, user.getPassword());
        assertEquals(USER_ROLE, user.getType());
    }
}
