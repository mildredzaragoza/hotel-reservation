package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.exceptions.APIUnprocessableEntity;
import com.aspire.webapp.model.User;
import com.aspire.webapp.utils.UserValidator;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserValidator userValidator;
	
	public void updatePassword(User user) throws Exception {
		try {
			userValidator.validate(user);
		    restTemplate.put("http://user-service/users/{username}", user, user.getUsername());
		}catch(APIUnprocessableEntity exception) {
			throw new APIUnprocessableEntity(exception.getMessage());
		}catch(Exception exception) {
			throw new NotFoundException();
		}
	}
}
