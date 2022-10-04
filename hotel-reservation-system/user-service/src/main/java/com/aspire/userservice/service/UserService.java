package com.aspire.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    public User updatePassword(String username, String password) throws NotFoundException {
        User userToUpdate = userRepository.findByUsername(username);
        if(userToUpdate == null){
        	throw new NotFoundException();
        }else{
        	userToUpdate.setPassword(passwordEncoder().encode(password));
        	return userRepository.save(userToUpdate);
        }
    }
}
