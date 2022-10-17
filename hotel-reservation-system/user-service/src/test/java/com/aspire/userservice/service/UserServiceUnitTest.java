package com.aspire.userservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@DisplayName("Test user service")
class UserServiceUnitTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		User user = new User("dev", "12345");
		
	//	when(userRepository.save(any(User.class))).thenReturn(user);
		
		User newUser = userService.updatePassword(user);
		Assertions.assertNotNull(userService.updatePassword(user));
	//	Assertions.assertEquals("dev", newUser.getUsername());
	}
}
