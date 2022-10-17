package com.aspire.webapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.model.User;

@SpringBootTest
@DisplayName("Test user service")
public class UserServiceIntegrationTest {
	@Autowired
	UserService userService;
	
	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		User user = new User("dev", "12345");
		ResponseEntity<User> responseEntity = userService.updatePassword(user);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK); 
	}
}