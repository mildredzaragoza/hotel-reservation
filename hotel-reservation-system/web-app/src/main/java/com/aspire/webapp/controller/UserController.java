package com.aspire.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.aspire.webapp.repository.UserRepository;


public class UserController {
	@Autowired
	UserRepository userRepository;
}
