package com.example.movie.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.movie.model.User;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    User userInput = new User("test", "test@email", "pass", "simple");

    @Test
    void testSaveAndFindByUsername() {
        userRepository.save(userInput);

        User found = userRepository.findByUsername("test");

        assertNotNull(found);
        assertEquals("test", found.getUsername());
        assertEquals("test@email", found.getEmail());
    }

    @Test
    void testFindByUsernameNotFound() {
        User found = userRepository.findByUsername("test2");

        assertNull(found);
    }
}
