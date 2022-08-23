package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.webapp.model.Users;
import com.aspire.webapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
    public Users findUserByUsername(String username){
    	if(userRepository.findByUsername(username) != null){
    		System.out.println("El  usuario fue encontrado");
    		return userRepository.findByUsername(username);
    	}else{
    		System.out.println("El  usuario no fue encontrado");
    		throw null;
    	}
}
}
