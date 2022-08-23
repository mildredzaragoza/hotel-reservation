package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aspire.webapp.model.Users;
import com.aspire.webapp.service.UserService;

@Controller
public class PasswordController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	{BasicConfigurator.configure();}
	
	@Autowired
	UserService userService;
	
	@GetMapping("/change-password")
	private String updatePassword(){
		return "change-password";
	}
	
	@PostMapping("/change-password")  
	private String changePasswrod(Model model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("re-password");
		logger.info("Username from request: " + username);
		logger.info("Password from request: " + password);
		logger.info("Re-password from request: " + repassword);
		if(password != repassword) {
			model.addAttribute("error", "Passwords must match");
		}else {
			Users userToUpdate = userService.findUserByUsername(username);
			logger.info("THE USERNAME FROM PASSWORD CONTROLLER: " + userToUpdate.getUsername());
		}
		return "change-password";
	}
}
