package com.aspire.guestregisterservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aspire.guestregisterservice.models.Guest;

@SpringBootTest
@DisplayName("Test CRUD operations")
class GuestServiceTest {
	@Autowired
	GuestService guestService;
	
	@Test
	@DisplayName("Test save guest")
	public void saveGuestTest() throws Exception {
		Guest guestOne = new Guest();
		guestOne.setName("Test 1");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		guestService.saveGuest(guestOne);
		assertTrue(guestOne.getIdGuest() != null);
		
		//Missing guest's data
		assertThrows(Exception.class, () -> guestService.saveGuest(new Guest()));
	}
	
	@Test
	@DisplayName("Test display all guests")
	public void getAllGuestTest() throws Exception {
		ArrayList<Guest> guests = guestService.getAllGuests();
		assertTrue(guests.size() > 0);
	}
	
	@Test
	@DisplayName("Test get guest by id")
	public void getGuestByIdTest() throws Exception {
		Guest guest = guestService.getGuestById(30L);
		assertTrue(guest != null);
		
		//Guest with id 80 does not exists
		assertThrows(Exception.class, () -> guestService.getGuestById(80L));
	}
	
	@Test
	@DisplayName("Test update guest")
	public void updateGuestTest() throws Exception {
		Guest guest = new Guest();
		guest.setName("Test One");
		guest.setEmail("test1@test.com");
		guest.setPhoneNumber("1523689715");
		guest.setTypeGuest("plus");
		guest.setCheckInDate("2022-08-09");
		guest.setCheckOutDate("2022-08-09");
		assertNotNull(guestService.updateGuest(30L, guest));
		assertThrows(Exception.class, () -> guestService.updateGuest(80L, guest)); 
		
		//Missing guest's data
		assertThrows(Exception.class, () -> guestService.updateGuest(30L, new Guest())); 
	}
	
	@Test 
	@DisplayName("Test delete guest")
	public void testDeleteGuest() throws Exception {
		assertTrue(guestService.deleteGuest(43L));
		assertThrows(Exception.class, () -> guestService.deleteGuest(80L)); 
		
	}
}
