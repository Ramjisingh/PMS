package com.accenture.pensiondisbursement.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accenture.pensiondisbursement.Cleint.AuthorizationClient;
import com.accenture.pensiondisbursement.Cleint.PensionerDetailCleint;
import com.accenture.pensiondisbursement.exception.TokenException;
import com.accenture.pensiondisbursement.exception.UserNotFound;
import com.accenture.pensiondisbursement.model.PensionerDetail;
import com.accenture.pensiondisbursement.model.ProcessPensionInput;
import com.accenture.pensiondisbursement.service.PensionDisbursementService;
import com.accenture.pensiondisbursement.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PensionDisbursementControllerTest {

	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	 @Mock
	    private PensionDisbursementService userService;
	    @Mock
	    private AuthorizationClient cleint;

	    @Mock
	    private PensionerDetailCleint pensionerDetailCleint;
	    @InjectMocks
	    private PensionDisbursementController pesionDisbursementController;

	    private MockMvc mockMvc;
	    
	@Test
	public void contextLoadsTestSuccess() {
		assertNotNull(pesionDisbursementController);
	}
	
	@Test
	public void disbursePensionTestSuccess() throws Exception {
		
		
		  ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		  PensionerDetail pensionerDetail =   new PensionerDetail("123456789","Lokendra","12/02/2000","BUWPTXYZ",50000.0,10000.0,"self","ICICI","9685037015","private");
	        Mockito.doReturn(responseEntity).when(cleint).authorization(token);
	        Mockito.doReturn(pensionerDetail).when(pensionerDetailCleint).pensionerDetailByAadhar(token, "123456789");
	        mockMvc = standaloneSetup(pesionDisbursementController).build();

		ProcessPensionInput processPensionInput = new ProcessPensionInput("123456789", 50550.0);
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/DisbursePension").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(processPensionInput)).header("Authorization", token));

        actions.andDo(MockMvcResultHandlers.print());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$").value(10));


	}
	
	@Test
	public void disbursePensionTestFail() throws Exception {
		
		
		  ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		  PensionerDetail pensionerDetail =   new PensionerDetail("123456789","Lokendra","12/02/2000","BUWPTXYZ",40000.0,10000.0,"self","ICICI","9685037015","private");
	        Mockito.doReturn(responseEntity).when(cleint).authorization(token);
	        Mockito.doReturn(pensionerDetail).when(pensionerDetailCleint).pensionerDetailByAadhar(token, "123456789");
	        mockMvc = standaloneSetup(pesionDisbursementController).build();

		ProcessPensionInput processPensionInput = new ProcessPensionInput("123456789", 50550.0);
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/DisbursePension").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(processPensionInput)).header("Authorization", token));

        actions.andDo(MockMvcResultHandlers.print());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$").value(21));


	}
	
	
	
	 @Test
	    void  disbursePensionWithIncorrectToken() throws Exception {
		 
		 ProcessPensionInput processPensionInput1 = new ProcessPensionInput("123456789",50550.0);
	       
	        Mockito.doThrow(new UserNotFound("INVALID TOKEN OR INVALID AADHAR NUMBER: PLEASE CHECK TOKEN OR AADHAR NUMBER")).when(cleint).authorization(token+"incorrect");
	        mockMvc = standaloneSetup(pesionDisbursementController).build();

	        ResultActions actions;
			try {

	
				 actions = mockMvc.perform(MockMvcRequestBuilders.post("/DisbursePension").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(processPensionInput1)).header("Authorization",  token+"incorrect"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
		        assertThrows(UserNotFound.class, () -> cleint.authorization(token+"incorrect"));
			}
	    }

//

//
//	@Test
//	public void disbursePensionTestFail() throws Exception {
//
//		ProcessPensionInput processPensionInput1 = new ProcessPensionInput("1234567891011120", 6069.98);
//		ResultActions actions = mockMvc.perform(get("/disbursePension").contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(processPensionInput1)).header("Authorization", "Bearer " + token));
//		actions.andExpect(status().isMethodNotAllowed());
//
//	}
//
//	@Test
//	public void getServiceChargeTestSuccess() throws Exception {
//
//		ResultActions actions = mockMvc.perform(post("/")
//				.contentType(MediaType.TEXT_PLAIN).content("Private").header("Authorization", "Bearer " + token));
//		actions.andExpect(status().isOk());
//		actions.andExpect(content().string("550.0"));
//
//	}
//
//	@Test
//	public void getServiceChargeTestFail() throws Exception {
//		ResultActions actions = mockMvc.perform(post("/getServiceCharge").contentType(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.TEXT_PLAIN).content("Sanyam").header("Authorization", "Bearer " + token));
//		actions.andExpect(status().isOk());
//		actions.andExpect(content().contentType("application/json"));
//		actions.andExpect(content().string("500.0"));
//	}


	public static String asJsonString(ProcessPensionInput processPensionInput) {
		try {
			return new ObjectMapper().writeValueAsString(processPensionInput);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
