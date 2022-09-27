package com.aspire.guestregisterservice.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aspire.guestregisterservice.models.Guest;

@SpringBootTest
class GuestServiceTest {
	@Autowired
	GuestService guestService;
	
	@Test
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
		
		//Missing email
		Guest guest = new Guest();
		guest.setName("Test 2");
		guest.setPhoneNumber("1523689715");
		guest.setTypeGuest("plus");
		guest.setCheckInDate("2022-08-09");
		guest.setCheckOutDate("2022-08-09");
		guestService.saveGuest(guest);
		Assertions.assertTrue(guest.getIdGuest() == null);
	}
	
	@Test
	public void getAllGuestTest() throws Exception {
		ArrayList<Guest> guests = guestService.getAllGuests();
		Assertions.assertTrue(guests.size() > 0);
	}
	
	@Test
	public void getGuestTest() throws Exception {
		Guest guest = guestService.getGuestById(30L).get();
		Assertions.assertTrue(guest != null);
	}
	
	@Test
	public void updateGuestTest() throws Exception {
		Guest guest = new Guest();
		guest.setName("Test One");
		guest.setEmail("test1@test.com");
		guest.setPhoneNumber("1523689715");
		guest.setTypeGuest("plus");
		guest.setCheckInDate("2022-08-09");
		guest.setCheckOutDate("2022-08-09");
		guestService.updateGuest(40L, guest);
	}
	
	@Test 
	public void testDeleteGuest() throws Exception {
		guestService.deleteGuest(39L);
	}
}
