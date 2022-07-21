package com.aspire.webapp.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.service.GuestInfo;

import antlr.collections.List;

@Controller
public class HomeController {
	@Autowired
    GuestInfo guestInfo;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/home")
	private String showHome() {
		return "index.jsp";
	}
	
	@GetMapping("/login")
	private String showLogIn() {
		return "html/sign-in.jsp";
	}
	
	@GetMapping("/guests")
	private String showGuests() {
		return "html/show-all-guest.jsp";
	}
	
	@RequestMapping("/admin")
	private String administratorView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username.equals("admin")) {
			return "html/admin-view.jsp";
		}else {
			return "html/guest-view.jsp";
		}
		
	}
	
	@RequestMapping("/forgot-password")
	private String changePasswrod() {
		return "html/change-password.jsp";
	}
	
	@RequestMapping("/guest-form")
	private String showGuestForm() {
		return "html/guest-form.jsp";
	}
	
    @PostMapping("/login")
    private String validateLogin(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session.setAttribute("username", username);
		
		if(username.equals("admin")) {
			return "html/admin-view.jsp";
		}else {
			return "html/guest-view.jsp";
		}
	}
    
    @GetMapping("/log-out")
	private String logOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "index.jsp";
	}
    
    @GetMapping("/guest-info")
    private String getGuests(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	java.util.List<Guest> guests = guestInfo.getGuests();
    	session.setAttribute("guestList", guests);
        return "html/show-all-guest.jsp";
    }
    
    @RequestMapping("/editguest?id={id}")
    private String editGuest(@PathVariable(value="id") Long id, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	Guest guest = guestInfo.getGuestById(id);
    	session.setAttribute("guest", guest);
		request.setAttribute("guest", guest);
		System.out.println("EL ID ES: " + guest.getIdGuest());
		System.out.println("EL GUEST ES: " + guest.getName());
		return "./guest-form";
    }
    
    @PostMapping("/add-guest")
    private String addGuest(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session.setAttribute("username", username);
		
		if(username.equals("admin")) {
			return "html/admin-view.jsp";
		}else {
			return "html/guest-view.jsp";
		}
	}
    
    @GetMapping("/delete-guest/{id}")
    private String deleteGuest(@PathVariable Long id){
    	guestInfo.deleteGuest(id);
        return "./home";
    }
    
    @GetMapping("/guest-form")
    private String getGuestForm(HttpServletRequest request){
        return "./html/guest-form.jsp";
    }
    
	
}
