package com.aspire.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}
	
	
/*	
    @PostMapping("/login")
    private String validateLogin(@Valid @ModelAttribute Users user, BindingResult result, HttpServletRequest request) {
    	System.out.println("username " + user.getUsername());
    	System.out.println("password " + user.getPassword());
    	HttpSession session = request.getSession();
    	if(!user.getUsername().equals("user")) {
    		return "/home";
    	}else {
    		session.setAttribute("username", user.getUsername());
    		return "/main";
    	}
	}   
*/
}
