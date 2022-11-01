package com.aspire.guestregisterservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.guestregisterservice.repository.GuestRepository;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTestMockito {
	
	@Mock
	private GuestRepository guestRepository;

    @Mock
    private GuestService guestService;
    
	@BeforeEach
    public void init() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void getAllGuestTest() throws Exception {
    	Assertions.assertNotNull(guestService.getAllGuests());
    }

}
