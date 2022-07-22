package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    private List<Guest> getAllGuest(){
        return guestService.getAllGuests();
    }

    @PostMapping
    private Guest saveGuest(@RequestBody Guest newGuest){
        return guestService.saveGuest(newGuest);
    }

    @GetMapping("/{id}")
    private Optional<Guest> getGuestById(@PathVariable Long id){
        try{
            return guestService.getGuestById(id);
        }catch(Exception exception){
            exception.printStackTrace(); 
        }
        return null;
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        try{
            return guestService.updateGuest(id, guest);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteGuest(@PathVariable Long id){
        return guestService.deleteGuest(id);
    }
}
