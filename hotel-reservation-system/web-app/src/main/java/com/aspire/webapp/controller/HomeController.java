package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.service.GuestInfo;

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
	
	@RequestMapping("/forgot-password")
	private String changePasswrod() {
		return "html/change-password.jsp";
	}
	
	@RequestMapping("/guest-form")
	private String showGuestForm() {
		return "html/guest-form.jsp";
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
	
    @PostMapping("/login")
    private String validateLogin(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		return "/admin";
	}
    
    @GetMapping("/log-out")
	private String logOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "index.jsp";
	}
    
    @RequestMapping("/guest-info")
    private String getGuests(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	java.util.List<Guest> guests = guestInfo.getGuests();
    	session.setAttribute("guestList", guests);
        return "html/show-all-guest.jsp";
    }
    
    @PostMapping("/add-guest")
    private String addGuest(HttpServletRequest request) {
		Guest newGuest = new Guest();		
		newGuest.setName(request.getParameter("name"));
		newGuest.setPhoneNumber(request.getParameter("phoneNumber"));
		newGuest.setEmail(request.getParameter("email"));
		newGuest.setCheckInDate(request.getParameter("checkindate"));
		newGuest.setCheckOutDate(request.getParameter("checkoutdate"));
		newGuest.setTypeGuest(request.getParameter("typeGuest"));
		guestInfo.addGuest(newGuest);
		return "/guest-info";
	}
    
    @RequestMapping("/edit-guest{id}")
    private String editGuest(@PathVariable(value="id") Long id, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	Guest guest = guestInfo.getGuestById(id);
    	session.setAttribute("guest", guest);
		request.setAttribute("guest", guest);
		return "/guest-form";
    }
    
    @PostMapping("/update-guest")
    private String updateGuest(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Guest demoGuest = (Guest) session.getAttribute("guest");
    	Long idGuest = demoGuest.getIdGuest();	
		demoGuest.setName(request.getParameter("name"));
		demoGuest.setPhoneNumber(request.getParameter("phoneNumber"));
		demoGuest.setEmail(request.getParameter("email"));
		demoGuest.setCheckInDate(request.getParameter("checkindate"));
		demoGuest.setCheckOutDate(request.getParameter("checkoutdate"));
		demoGuest.setTypeGuest(request.getParameter("typeGuest"));
		guestInfo.updateGuest(idGuest, demoGuest);
		return "/guest-info";
	}
    
    @RequestMapping("/delete-guest{id}")
    private String deleteGuest(@PathVariable Long id){
    	guestInfo.deleteGuest(id);
        return "/guest-info";
    }
}
