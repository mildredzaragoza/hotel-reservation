package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aspire.webapp.exceptions.ApplicationException;
import com.aspire.webapp.model.Guest;
import com.aspire.webapp.service.BookService;

@Controller
public class CrudController {
	@Autowired
    BookService bookService;
	
    @RequestMapping("/guests")
    private String getGuests(Model model){
    	model.addAttribute("guestList", bookService.getGuests());
        return "show-all-guest";
    }
    
    @PostMapping("/add-guest")
    private String addGuest(@Valid @ModelAttribute Guest guest, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		throw new ApplicationException("It was not possible add the guest");
    	}else {
    		bookService.addGuest(guest);
    		model.addAttribute("guestList", bookService.getGuests());
            return "show-all-guest";
    	}
    }
    
    @RequestMapping("/edit-guest-{id}")
    private String editGuest(@PathVariable("id") Long id, Model model, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	model.addAttribute("guest", bookService.getGuestById(id));
    	session.setAttribute("id", id);
		return "/guest-form";
    }
       
    @PostMapping("/update-guest")
    private String updateGuest(@Valid @ModelAttribute Guest guest, BindingResult result, HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	if(result.hasErrors()) {
    		throw new ApplicationException("It was not possible update");
    	}else { 
    		bookService.updateGuest((Long)session.getAttribute("id"), guest);
    	}
		session.removeAttribute("id");
    	model.addAttribute("guestList", bookService.getGuests());
        return "show-all-guest";
    }
    
    @RequestMapping("/delete-guest-{id}")
    private String deleteGuest(@PathVariable("id") Long id, Model model){
    	bookService.deleteGuest(id);
    	model.addAttribute("guestList", bookService.getGuests());
        return "show-all-guest";
    }
}
