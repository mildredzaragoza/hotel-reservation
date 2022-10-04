package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    	try {
			return guestService.getAllGuests();
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
    }

    @ApiOperation(value = "Saves new guest", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest saved"),
            @ApiResponse(responseCode = "422", description = "Guest's data is incomplete"),
    })
    @PostMapping
    private Guest saveGuest(@RequestBody Guest newGuest){
        try {
			return guestService.saveGuest(newGuest);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
    }

    @ApiOperation(value = "Finds guest by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest found"),
            @ApiResponse(responseCode = "404", description = "Guest not found"),
    })
    @GetMapping("/{id}")
    private ResponseEntity<Guest> getGuestById(@Parameter(description = "id of guest to be searched") @PathVariable Long id){
        try{
        	return new ResponseEntity<Guest>(guestService.getGuestById(id).get(), HttpStatus.OK);
        }catch(Exception exception){
        	System.out.println(exception.getMessage());
            return new ResponseEntity<Guest>(new Guest(), HttpStatus.NOT_FOUND);
        }
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
            @ApiResponse(responseCode = "200", description = "Guest deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Guest to delete not found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGuest(@Parameter(description = "id of guest to be deleted") @PathVariable Long id){
        try {
			return new ResponseEntity<Boolean>(guestService.deleteGuest(id), HttpStatus.OK);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
    }
}
