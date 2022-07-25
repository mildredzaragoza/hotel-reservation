package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @ApiOperation(value = "Gets all guests registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guests found"),
            @ApiResponse(responseCode = "404", description = "Guests not found"),
    })
    @GetMapping
    private List<Guest> getAllGuest(){
        return guestService.getAllGuests();
    }

    @ApiOperation(value = "Saves new guest", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest saved"),
            @ApiResponse(responseCode = "422", description = "Guest's data is incomplete"),
    })
    @PostMapping
    private Guest saveGuest(@RequestBody Guest newGuest){
        return guestService.saveGuest(newGuest);
    }

    @ApiOperation(value = "Finds guest by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest found"),
            @ApiResponse(responseCode = "404", description = "Guest not found"),
    })
    @GetMapping("/{id}")
    private Optional<Guest> getGuestById(@Parameter(description = "id of guest to be searched") @PathVariable Long id){
        try{
            return guestService.getGuestById(id);
        }catch(Exception exception){
            exception.printStackTrace(); 
        }
        return null;
    }

    @ApiOperation(value = "Updates a guest searching by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest updated"),
            @ApiResponse(responseCode = "404", description = "Guest to update not found"),
    })
    @PutMapping("/{id}")
    public Guest updateGuest(@Parameter(description = "id of guest to be updated") @PathVariable Long id, @RequestBody Guest guest) {
        try{
            return guestService.updateGuest(id, guest);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "Deletes guest by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest deleted"),
            @ApiResponse(responseCode = "404", description = "Guest to delete not found"),
    })
    @DeleteMapping("/{id}")
    public boolean deleteGuest(@Parameter(description = "id of guest to be deleted") @PathVariable Long id){
        return guestService.deleteGuest(id);
    }
}
