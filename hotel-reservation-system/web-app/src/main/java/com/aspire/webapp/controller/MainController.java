package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.model.Users;
import com.aspire.webapp.service.BookService;


@Controller
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	{BasicConfigurator.configure();}
	
	@Autowired
    BookService bookService;
	
	@GetMapping({"/home", "/"})
	private String showHome() {
		return "index.html";
	}
	
	@RequestMapping("/main")
	private String administratorView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("guest");
		session.setAttribute("username","admin");
		return "main-view.html";
	}
	
    @RequestMapping("/guests")
    private String getGuests( Model model){
    	java.util.List<Guest> guests = bookService.getGuests();
    	model.addAttribute("guestList", guests);
        return "show-all-guest.html";
    }
	
	@RequestMapping("/guest-form")
	private String showGuestForm() {
		return "guest-form.html";
	}
	
	@PostMapping("/update-password") 
	private String changePasswrod(@Valid @ModelAttribute Users user, BindingResult result, HttpServletRequest request) {
		return "html/sign-in.jsp";
	}
	
	@GetMapping("/update-password")
	private String updatePassword(){
		return "html/change-password.jsp";
	}

    @GetMapping("/logout")
	private String logOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "index.html";
	}
     
    @PostMapping("/add-guest")
    private String addGuest(@Valid @ModelAttribute Guest guest, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "guest-form.html";
    	}else {
    		bookService.addGuest(guest);
        	java.util.List<Guest> guests = bookService.getGuests();
        	model.addAttribute("guestList", guests);
            return "show-all-guest.html";
    	}
    }
    
    @RequestMapping("/edit-guest-{id}")
    private String editGuest(@PathVariable(value="id") Long id, Model model, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	Guest guest = bookService.getGuestById(id);
    	model.addAttribute("guest", guest);
    	session.setAttribute("guest", guest);
		return "/guest-form";
    }
       
    @PostMapping("/update-guest")
    private String updateGuest(@Valid @ModelAttribute Guest guest, BindingResult result, HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Guest guestToUpdate = (Guest) session.getAttribute("guest");
    	if(result.hasErrors()) {
    		return "/guest-form";
    	}else {
    		bookService.updateGuest(guestToUpdate.getIdGuest(), guest);
    	}
		session.removeAttribute("guest");
		return "/guests";
    }
    
    
    @RequestMapping("/delete-guest-{id}")
    private String deleteGuest(@PathVariable Long id, Model model){
    	bookService.deleteGuest(id);
    	java.util.List<Guest> guests = bookService.getGuests();
    	model.addAttribute("guestList", guests);
        return "show-all-guest.html";
    }
}
