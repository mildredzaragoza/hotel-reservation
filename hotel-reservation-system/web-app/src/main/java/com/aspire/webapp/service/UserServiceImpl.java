package com.aspire.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aspire.webapp.model.CustomUserDetails;
import com.aspire.webapp.model.Users;
import com.aspire.webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl() {
		super();
	}
	
	@Override
	public Users updatePassword(Users user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User notttttt found");
		}
		return new CustomUserDetails(user);
	}
}
