package com.example.bpdts.BPDTSTEST;

import com.example.bpdts.BPDTSTEST.models.User;
import com.example.bpdts.BPDTSTEST.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BpdtstestApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser() {
		User andrew = new User("Andrew", "Watson", "awatson.work@gmail.com", "1.234.567.89", 55.1270, 1.5103, "London");
		userRepository.save(andrew);
	}
}
