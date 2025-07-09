package com.example.movie.service;

import com.example.movie.exception.UserAlreadyLoggedInException;
import com.example.movie.exception.UserAlreadyLoggedOutException;
import com.example.movie.exception.UserExistsException;
import jakarta.transaction.Transactional;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.movie.model.User;
import com.example.movie.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

// A little explanation:
// I can't use Function with @BeforeEach to sign up and logout
// because it will execute before every test and that will cause some of them to fail.

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    User userInput = new User("test", "test@email", "pass", "simple");

    @Test
    void testSignUpSuccess() {
        userService.signup(userInput);

        User found = userRepository.findByUsername("test");
        assertNotNull(found);
        assertNotNull(found.getId());
    }

    @Test
    void testSignUpFailExists() throws UserExistsException {
        userService.signup(userInput);
        userService.logout();
        assertThrows(UserExistsException.class, () -> {
            userService.signup(userInput);
        });
    }

//    the function above also tests the logout function

    @Test
    void testSignUpFailLoggedIn() throws UserAlreadyLoggedInException {
        userService.signup(userInput);

        assertThrows(UserAlreadyLoggedInException.class, () -> {
            userService.signup(userInput);
        });
    }

    @Test
    void testLoginSuccess() {
        userService.signup(userInput);
        userService.logout();
        userService.login(userInput);

        Object userId = session.getAttribute("userId");
        Object username = session.getAttribute("username");

        assertNotNull(userId);
        assertEquals("test", username);
    }

    @Test
    void testLogInAlreadyLoggedIn() throws UserAlreadyLoggedInException {
        userService.signup(userInput);
        userService.logout();
        userService.login(userInput);

        assertThrows(UserAlreadyLoggedInException.class, () -> {
            userService.login(userInput);
        });
    }

    @Test
    void testLogOutAlreadyLoggedOut() throws UserAlreadyLoggedOutException {
        userService.signup(userInput);
        userService.logout();
        assertThrows(UserAlreadyLoggedOutException.class, () -> {
            userService.logout();
        });
    }
}
