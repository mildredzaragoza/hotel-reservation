package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aspire.webapp.model.Users;
import com.aspire.webapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    public Users findUserByUsername(String username){
    	if(userRepository.findByUsername(username) != null){
    		return userRepository.findByUsername(username);
    	}else{
    		return null;
    	}
    }
    
    public Users updatePassword(String username, String password) throws NotFoundException {
        Users userToUpdate = findUserByUsername(username);
        if(userToUpdate == null){
        	throw new NotFoundException();
        }else{
        	userToUpdate.setPassword(passwordEncoder.encode(password));
        	return userRepository.save(userToUpdate);
        }
    }
}
