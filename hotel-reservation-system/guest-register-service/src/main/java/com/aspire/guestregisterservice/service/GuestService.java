package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public ArrayList<Guest> getAllGuests(){
        return (ArrayList<Guest>) guestRepository.findAll();
    }

    public Guest saveGuest(Guest guest)  {
        return guestRepository.save(guest);
    }

    public Optional<Guest> getGuestById(Long id) throws Exception {
       /*       Guest guest = null;
            try {
                guest = guestRepository.findById(id)
                        .orElseThrow(() -> new Exception("Does not exist guest with id " + id ));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return ResponseEntity.ok(guest);

      */
        if(!guestRepository.findById(id).isEmpty()){
            return guestRepository.findById(id);
        }else{
            throw new Exception("Guest not found with id " + id);
        }
    }

    public Guest updateGuest(Long id, Guest guest) throws Exception {
        Guest guestToUpdate = null;
        if(!guestRepository.findById(id).isEmpty()){
            guestToUpdate = guestRepository.findById(id).orElseThrow(() -> new Exception("Guest to update doesn't exist."));
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckInDate());
            return guestRepository.save(guestToUpdate);
        }else{
            throw new Exception("Guest to update doesn't exist.");
        }
    }

    public boolean deleteGuest(Long id){
        try {
            guestRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }


}
