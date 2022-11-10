package com.aspire.webapp.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.aspire.webapp.model.Guest;
import com.aspire.webapp.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(CrudController.class)
class CrudControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("Get all guests test")
	public void getGuestTest() throws Exception {
	//	ResponseEntity<Guest> guestOne = new ResponseEntity<Guest>(new Guest(), HttpStatus.OK);
		ResponseEntity<Guest[]> guests = null;
		when(bookService.getGuests()).thenReturn(guests);
		mockMvc.perform(MockMvcRequestBuilders.get("/guests"))
      //  .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attribute("guestList", guests))
        .andExpect(MockMvcResultMatchers.view().name("show-all-guest"))
        .andDo(MockMvcResultHandlers.print());
	}
	

}
