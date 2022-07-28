package com.aspire.webapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.aspire.webapp.model.Users;

@Service
public interface UserService extends UserDetailsService {
	public Users updatePassword(Users user);
	
}
