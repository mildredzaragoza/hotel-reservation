package com.aspire.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
public class HomeController {
	@Autowired
    BookService bookService;
	
	@GetMapping({"/home", "/"})
	private String showHome() {
		return "index.jsp";
	}
	
	@GetMapping("/login")
	private String showLogIn(Model model, @RequestParam(required=false) String error) {
		model.addAttribute("error", error);
		return "html/sign-in.jsp";
	}
	
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
	
	@PostMapping("/update-password") 
	private String changePasswrod(@Valid @ModelAttribute Users user, BindingResult result, HttpServletRequest request) {
	//	String repassword = request.getParameter("re-password");
		return "html/sign-in.jsp";
	}
	
	@GetMapping("/update-password")
	private String updatePassword(){
	//	Users user = new Users();		
	/*	user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		System.out.println("NEW USER IS: " + user.toString());
		userService.updatePassword(user); */
	//	model.addAttribute("user", userService.updatePassword(user));
		return "html/change-password.jsp";
	}

	@RequestMapping("/guest-form")
	private String showGuestForm() {
		return "html/guest-form.jsp";
	}
	
	@RequestMapping("/main")
	private String administratorView(HttpServletRequest request) {
		HttpSession session = request.getSession();
	//	String username = (String) session.getAttribute("username");
		session.removeAttribute("guest");
	//	if(username.equals("admin")) { */
			session.setAttribute("username","admin");
			return "html/admin-view.jsp";
	/*	}else {
			return "html/guest-view.jsp";
		}	*/
	}
  
    @GetMapping("/logout")
	private String logOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "index.jsp";
	}
    
    @RequestMapping("/guests")
    private String getGuests(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	java.util.List<Guest> guests = bookService.getGuests();
    	session.setAttribute("guestList", guests);
        return "html/show-all-guest.jsp";
    }
 
    @PostMapping("/add-guest")
    private String addGuest(@Valid @ModelAttribute Guest guest, BindingResult result) {
    	if(result.hasErrors()) {
    		return "/guest-form";
    	}else {
    		bookService.addGuest(guest);
    		return "/guests";
    	}
    }
    
    @RequestMapping("/edit-guest-{id}")
    private String editGuest(@PathVariable(value="id") Long id, HttpServletRequest request){
    	HttpSession session = request.getSession();
    	Guest guest = bookService.getGuestById(id);
    	session.setAttribute("guest", guest);
		return "/guest-form";
    }
       
    @PostMapping("/update-guest")
    private String updateGuest(@Valid @ModelAttribute Guest guest, BindingResult result, HttpServletRequest request) {
    	System.out.println("UPDATEEEEEE " + guest.getName());
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
    private String deleteGuest(@PathVariable Long id){
    	bookService.deleteGuest(id);
        return "/guests";
    }
}
