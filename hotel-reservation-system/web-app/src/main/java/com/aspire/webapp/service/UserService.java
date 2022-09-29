package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;
	
	public void updatePassword(String username, String password) throws NotFoundException {
		try {
		    restTemplate.put("http://user-service/users/{username}", password, username);
		}catch(Exception exception) {
			throw new NotFoundException();
		}
		
	}
}
