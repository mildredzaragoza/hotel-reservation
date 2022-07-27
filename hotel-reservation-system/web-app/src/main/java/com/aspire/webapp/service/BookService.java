package com.aspire.webapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.model.Guest;

@Service
public class BookService {
	private static final HttpStatus INTERNAL_SERVER_ERROR = null;
	@Autowired
	private RestTemplate restTemplate;
	
    public ResponseEntity<Guest> addGuest(Guest guest) { 
    	try {
    		return restTemplate.postForEntity("http://guest-register-service/guests", guest, Guest.class);
    	}catch(Exception exception) {
    		return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    	}
    }
	
    public List<Guest> getGuests() {
    	try {
    		return Arrays.asList(restTemplate.getForObject("http://guest-register-service/guests", Guest[].class));
    	}catch(Exception exception) {
    		Guest defaultGuest = new Guest();
    		defaultGuest.setIdGuest(null);
    		defaultGuest.setName(null);
    		defaultGuest.setEmail(null);
    		defaultGuest.setCheckInDate(null);
    		defaultGuest.setCheckOutDate(null);
    		defaultGuest.setPhoneNumber(null);
    		defaultGuest.setTypeGuest(null);
    		List<Guest> defaultList = new ArrayList<>(Arrays.asList(defaultGuest));
            return defaultList;
    	}
    }
    
    public Guest getGuestById(Long id) {
    	return restTemplate.getForObject("http://guest-register-service/guests/{id}", Guest.class, id);
    }
    
    public void updateGuest(Long id, Guest guest) {
    	restTemplate.put("http://guest-register-service/guests/{id}", guest, id);
    }
    
    public void deleteGuest(Long id) {
    	 restTemplate.delete("http://guest-register-service/guests/{id}", id);
    }
    

}
