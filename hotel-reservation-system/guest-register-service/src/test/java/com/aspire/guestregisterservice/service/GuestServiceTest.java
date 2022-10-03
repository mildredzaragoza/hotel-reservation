package com.aspire.guestregisterservice.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
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
		Assertions.assertTrue(guestOne.getIdGuest() != null);
		
		//Missing guest's data
		Guest guest = new Guest();
		guest.setName("Test 2");
		Assertions.assertThrows(Exception.class, () -> guestService.saveGuest(guest));
	}
	
	@Test
	@DisplayName("Test display all guests")
	public void getAllGuestTest() throws Exception {
		ArrayList<Guest> guests = guestService.getAllGuests();
		Assertions.assertTrue(guests.size() > 0);
	}
	
	@Test
	@DisplayName("Test get guest by id")
	public void getGuestTest() throws Exception {
		Guest guest = guestService.getGuestById(30L).get();
		Assertions.assertTrue(guest != null);
		
		//Guest with id 80 does not exists
		Assertions.assertThrows(Exception.class, () -> guestService.getGuestById(80L).get());
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
		Assertions.assertNotNull(guestService.updateGuest(30L, guest));
		Assertions.assertThrows(Exception.class, () -> guestService.updateGuest(80L, guest)); 
		
		//Missing guest's data
		Guest guestTwo = new Guest();
		guestTwo.setName("Test Two");
		Assertions.assertThrows(Exception.class, () -> guestService.updateGuest(30L, guestTwo)); 
		
	}
	
	@Test 
	@DisplayName("Test delete guest")
	public void testDeleteGuest() throws Exception {
		guestService.deleteGuest(39L);
	}
}
