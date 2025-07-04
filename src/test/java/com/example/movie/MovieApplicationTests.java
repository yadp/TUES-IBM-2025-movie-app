package com.example.movie;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@Transactional
class MovieApplicationTests {
	@Test
	void contextLoads() {
		System.out.println("Context loaded successfully");
	}
}
