package com.aspire.guestregisterservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.guestregisterservice.models.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;

public class GuestServiceTestMockito {
	
	@Mock
	private Guest guest;
	
	@Mock
	private GuestRepository guestRepository;

	@InjectMocks
    private GuestService guestService;
    
	@BeforeEach
    public void init() {
    	MockitoAnnotations.openMocks(this);
    }
	
    @Test
    @DisplayName("Test mock creation")
    public void testMockCreation(){
    	assertNotNull(guest);
        assertNotNull(guestRepository);
        assertNotNull(guestService);
    }
    
    @Test
    @DisplayName("Get all guest test")
    public void getAllGuestTest() throws Exception {
    	ArrayList<Guest> guests = new ArrayList<Guest>();
    	when(guestRepository.findAll()).thenReturn(guests);
        guestService.getAllGuests();
        verify(guestRepository).findAll();
    }

    @Test
    @DisplayName("Save new guest test")
    public void saveGuestTest() throws Exception {
    	when(guestRepository.save(any(Guest.class))).thenReturn(new Guest());
    	guestService.saveGuest(new Guest());
    	verify(guestRepository).save(any(Guest.class));
    }
    
    @Test
    @DisplayName("Update guest test")
    public void updateTest() throws Exception {
    	when(guestRepository.save(any(Guest.class))).thenReturn(new Guest());
    	guestService.updateGuest(1L, new Guest());
    	verify(guestRepository).save(any(Guest.class));
    }
}
