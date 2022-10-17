package com.aspire.userservice.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;
import com.aspire.userservice.utils.exception.UserNotFound;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    public User updatePassword(User user) throws Exception {
		try{
			User userToUpdate = userRepository.findByUsername(user.getUsername()).get();
			System.out.println("USERNAMEEE " + userToUpdate.getUsername());
	    	userToUpdate.setPassword(passwordEncoder().encode(user.getPassword()));
	    	return userRepository.save(userToUpdate);
		}catch(NoSuchElementException exception) {
			exception.printStackTrace();
			throw new UserNotFound("User to update not found.");
		}catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception("Something went wrong");
		}  
    }
}
