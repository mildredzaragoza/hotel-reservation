package com.aspire.userservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aspire.userservice.model.User;


@SpringBootTest
@DisplayName("Test user service")
class UserServiceIntegrationTest {

	@Autowired
	UserService userService;

	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		User user = new User();
		user.setUsername("dev");
		user.setPassword("123456");
		Assertions.assertNotNull(userService.updatePassword(user));
		Assertions.assertThrows(Exception.class, () -> userService.updatePassword(new User())); 
	}
}
