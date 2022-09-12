package com.aspire.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aspire.webapp.model.Password;
import com.aspire.webapp.service.UserService;

@Controller
public class PasswordController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/change-password")
	private String updatePassword(){
		return "change-password";
	}
	
	@PostMapping("/change-password")  
	private String changePasswrod(@ModelAttribute Password password, Model model) {	
		if(!password.getPassword().equals(password.getRepassword())) {
			model.addAttribute("error", "Passwords must match");
		}else {
			try{
				userService.updatePassword(password.getUsername(), password.getPassword());
				model.addAttribute("successful", "Password changed successfully. /nPlease login.");
			}catch(NotFoundException e) {
				model.addAttribute("errorUsername", "User not found");
			}
		}
		return "change-password";
	}
}
