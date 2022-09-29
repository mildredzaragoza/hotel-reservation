package com.aspire.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
    @PutMapping("/{username}")
    public User updatePassword(@PathVariable String username, @RequestBody String password) throws NotFoundException {
        try{
            return userService.updatePassword(username, password);
        }catch(Exception exception){
            exception.printStackTrace();
            throw new NotFoundException();
        }
    }
}
