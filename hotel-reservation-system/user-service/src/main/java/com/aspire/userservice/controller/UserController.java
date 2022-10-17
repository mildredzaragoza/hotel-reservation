package com.aspire.userservice.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;
import com.aspire.userservice.utils.validator.UserValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
    @ApiOperation(value = "Update user's password", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "404", description = "User to update not found"),
            @ApiResponse(responseCode = "422", description = "Password is required"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
    })
    @PutMapping("/{username}")
    public User updatePassword(@PathVariable String username, @RequestBody User user) throws Exception{ 
        userValidator.validate(user);
        return userService.updatePassword(user);
    /*	
        try{
        	return new ResponseEntity<User>(userService.updatePassword(username, password), HttpStatus.OK);
        }catch(NoSuchElementException exception){
        	System.out.println(exception.getMessage());
        	return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }catch(APIUnprocessableEntity exception) { 
			System.out.println(exception.getMessage());
			return new ResponseEntity<User>(new User(), HttpStatus.UNPROCESSABLE_ENTITY);
    	}catch(Exception exception){
    		System.out.println(exception.getMessage());
    		 return new ResponseEntity<User>(new User(), HttpStatus.BAD_GATEWAY);
        }
       */ 
    }
}
