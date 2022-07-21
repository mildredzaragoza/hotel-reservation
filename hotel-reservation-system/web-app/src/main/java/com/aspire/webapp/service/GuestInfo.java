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
	
    public List<Guest> getGuests() {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8081/guests", Guest[].class));
    }
    
    public Guest getGuestById(Long id) {
    	return restTemplate.getForObject("http://localhost:8081/guests/{id}", Guest.class, id);
    }
    
    public ResponseEntity<Guest> updateGuest(Long id) {
    	return restTemplate.getForEntity("http://localhost:8081/guests/{id}", Guest.class, id);
    }
    
    public void deleteGuest(Long id) {
    	 restTemplate.delete("http://localhost:8081/guests/" + id);
    }
}
