package com.example.movie;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.movie.repository.UserRepository;
import com.example.movie.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@Transactional
class MovieApplicationTests {
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private UserRepository userRepository;

	@Test
	void contextLoads() {
		System.out.println("Context loaded successfully");
	}
}
