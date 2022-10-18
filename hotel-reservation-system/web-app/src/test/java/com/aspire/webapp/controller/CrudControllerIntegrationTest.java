package com.aspire.webapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import com.aspire.webapp.model.Guest;

@SpringBootTest
@DisplayName("Test CRUD controller")
class CrudControllerIntegrationTest {
	
	@Autowired
	private CrudController crudController;

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	@DisplayName("Test add guest")
	public void addGuestTest() {
		Guest guestOne = new Guest();
		guestOne.setName("Test One");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		String output = crudController.addGuest(guestOne, null);
		assertEquals(output, "forward:/guests");
	//	assertThrows(Exception.class, () -> bookService.addGuest(new Guest()
	}

}
