package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest saveGuest(Guest guest) throws Exception{
    	try {
    		return guestRepository.save(guest);
    	}catch(DataIntegrityViolationException exception) {
    		throw new DataIntegrityViolationException("Unregistered guest, all fields are required. Verify the guest's information.");
    	}catch (Exception exception) {
    		throw new Exception("Something went wrong, try again.");	
    	}
        
    }
    
    public ArrayList<Guest> getAllGuests() throws Exception{
    	if(!guestRepository.findAll().isEmpty()){
    		return (ArrayList<Guest>) guestRepository.findAll();	
        }else{
            throw new Exception("There are no guests");	
    	}
    }

    public Guest getGuestById(Long id) throws Exception {
       try {
    	   return guestRepository.findById(id).get();
       }catch(NoSuchElementException exception) {
    	   throw new NoSuchElementException("Guest with ID " + id + " doesn't exist");
       }catch(Exception exception) {
    	   throw new Exception("Something went wrong, try again.");
       }
    }

    public Guest updateGuest(Long id, Guest guest) throws Exception {
    	try {
    		Guest guestToUpdate = guestRepository.findById(id).get();
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckOutDate());
            return guestRepository.save(guestToUpdate);
    	}catch(NoSuchElementException exception) {
    		throw new NoSuchElementException("Guest to update doesn't exist.");
    	}catch(DataIntegrityViolationException exception) {
    		throw new DataIntegrityViolationException("All fields are required to update. Verify the guest's information.");
    	}catch(Exception exception) {
    		throw new Exception("Something went wrong");
    	}  
    }

    public boolean deleteGuest(Long id) throws Exception{
        try {
            guestRepository.deleteById(id);
            return true;
        }catch(EmptyResultDataAccessException exception) {
        	throw new EmptyResultDataAccessException("Does not exist guest with ID " + id, 1);
    	}catch (Exception exception){
        	throw new Exception("Something went wrong");
        }
    }


}
