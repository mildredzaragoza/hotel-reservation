package com.aspire.webapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aspire.webapp.model.Guest;

@Service
public class GuestInfo {
	@Autowired
	private RestTemplate restTemplate;
	
    public ResponseEntity<Guest> addGuest(Guest guest) { 
    	return restTemplate.postForEntity("http://guest-register-service/guests", guest, Guest.class);
    }
	
    public List<Guest> getGuests() {
        return Arrays.asList(restTemplate.getForObject("http://guest-register-service/guests", Guest[].class));
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
