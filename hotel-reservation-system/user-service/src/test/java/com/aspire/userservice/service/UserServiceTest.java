package com.aspire.userservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DisplayName("Test user service")
class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		String username = "dev";
		String password = "12345";
		Assertions.assertNotNull(userService.updatePassword(username, password));
		Assertions.assertThrows(Exception.class, () -> userService.updatePassword("dev", password)); 
	}
}
