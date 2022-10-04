package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public Guest saveGuest(Guest guest) throws Exception{
    	try {
    		return guestRepository.save(guest);
    	}catch (Exception exception) {
    		throw new Exception("Unregistered guest, all fields are required. Verify the guest's information.");	
    	}
        
    }
    
    public ArrayList<Guest> getAllGuests() throws Exception{
    	if(!guestRepository.findAll().isEmpty()){
    		return (ArrayList<Guest>) guestRepository.findAll();	
        }else{
            throw new Exception("There are no guests");	
    	}
    }

    public Optional<Guest> getGuestById(Long id) throws Exception {
    	if(!guestRepository.findById(id).isEmpty()){
            return guestRepository.findById(id);
        }else{
            throw new Exception("Guest not found with ID " + id);
        }
    }

    public Guest updateGuest(Long id, Guest guest) throws Exception {
        Guest guestToUpdate = null;
        if(!guestRepository.findById(id).isEmpty()){
            guestToUpdate = guestRepository.findById(id).get();
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckOutDate());
            return guestRepository.save(guestToUpdate);
        }else{
        //	throw new NotFoundException();
            throw new Exception("Guest to update doesn't exist.");
        }
    }

    public boolean deleteGuest(Long id) throws Exception{
        try {
            guestRepository.deleteById(id);
            return true;
        }catch (Exception exception){
        	throw new Exception("Does not exist guest with ID " + id);
        }
    }


}
