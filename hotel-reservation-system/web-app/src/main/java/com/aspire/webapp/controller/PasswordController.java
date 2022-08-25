package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
		
		if(!password.equals(repassword)) {
			model.addAttribute("error", "Passwords must match");
		}else {
			try{
				userService.updatePassword(username, password);
				model.addAttribute("succesful", "Password changed succesfully. Please login.");
			}catch(NotFoundException e) {
				model.addAttribute("errorUsername", "User not found");
			}
		}
		return "change-password";
	}
}
