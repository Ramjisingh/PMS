package com.accenture.processpension.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

import com.accenture.processpension.exception.TokenException;
import com.accenture.processpension.exception.WrongUserException;
import com.accenture.processpension.Model.PensionerDetail;
import com.accenture.processpension.Model.PensionerInput;
//import com.accenture.processpension.exception.UserNotFound;
//import com.accenture.processpension.model.PensionerDetail;
import com.accenture.processpension.Model.ProcessPensionInput;
import com.accenture.processpension.client.AuthorizationClient;
import com.accenture.processpension.client.PensionerDetailCleint;
import com.accenture.processpension.client.PensionerDisbursementCleint;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProcessPensionControllerTest {

	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	@Mock
	private AuthorizationClient cleint;

	@Mock
	private PensionerDetailCleint pensionerDetailCleint;
	@InjectMocks
	private ProcessPensionController processPensionController;
	@Mock
	private PensionerDisbursementCleint pensionerDisbursementCleint;

	private MockMvc mockMvc;

	@Test
	public void contextLoadsTestSuccess() {
		assertNotNull(processPensionController);
	}

	@Test
	public void disbursePensionTestSuccess() throws Exception {

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		ProcessPensionInput processPensionInput = new ProcessPensionInput("123456789", 50550.0);

		Mockito.doReturn(10).when(pensionerDisbursementCleint).disbursePension(token, processPensionInput);

		mockMvc = standaloneSetup(processPensionController).build();

		ResultActions actions = mockMvc
				.perform(MockMvcRequestBuilders.post("/ProcessPension").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(processPensionInput)).header("Authorization", token));

		actions.andDo(MockMvcResultHandlers.print());
		actions.andExpect(MockMvcResultMatchers.jsonPath("$").value(10));

	}


	@Test
	public void disbursePensionTestFail() throws Exception {

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		ProcessPensionInput processPensionInput = new ProcessPensionInput("123456789", 50550.0);

		Mockito.doReturn(21).when(pensionerDisbursementCleint).disbursePension(token, processPensionInput);

		mockMvc = standaloneSetup(processPensionController).build();

		ResultActions actions = mockMvc
				.perform(MockMvcRequestBuilders.post("/ProcessPension").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(processPensionInput)).header("Authorization", token));

		actions.andDo(MockMvcResultHandlers.print());
		actions.andExpect(MockMvcResultMatchers.jsonPath("$").value(21));

	}

//	
//	
//	
	@Test
	void disbursePensionWithIncorrectToken() throws Exception {

		ProcessPensionInput processPensionInput1 = new ProcessPensionInput("123456789", 50550.0);

		Mockito.doThrow(new WrongUserException("USER INPUT IS WRONG OR TOKEN IS WRONG")).when(cleint)
				.authorization(token + "incorrect");
		mockMvc = standaloneSetup(processPensionController).build();

		ResultActions actions;
		try {

			actions = mockMvc
					.perform(MockMvcRequestBuilders.post("/ProcessPension").contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(processPensionInput1)).header("Authorization", token + "incorrect"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertThrows(WrongUserException.class, () -> cleint.authorization(token + "incorrect"));
		}
	}

	@Test
	void disbursePensionWithIncorrectToken2() throws Exception {

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.UNAUTHORIZED);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);

		ProcessPensionInput processPensionInput1 = new ProcessPensionInput("123456789", 50550.0);

		mockMvc = standaloneSetup(processPensionController).build();

		ResultActions actions;
		try {

			actions = mockMvc
					.perform(MockMvcRequestBuilders.post("/ProcessPension").contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(processPensionInput1)).header("Authorization", token));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertThrows(WrongUserException.class, () -> processPensionController.disbursePension(token,processPensionInput1));
		}
	}

//

	@Test
	void pensionDetailWithCorrectTokenAndInput() throws Exception {
		PensionerDetail pensionerDetail = new PensionerDetail("123456789", "Lokendra", "12/02/2000", "BUWPTXYZ",
				50000.0, 10000.0, "self", "ICICI", "9685037015", "private");
		Mockito.doReturn(pensionerDetail).when(pensionerDetailCleint).pensionerDetailByAadhar(token, "123456789");

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		mockMvc = standaloneSetup(processPensionController).build();
		PensionerInput input = new PensionerInput("Lokendra", "12/02/2000", "BUWPTXYZ", "123456789", "self");
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/PensionDetail")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(input)).header("Authorization", token));
		actions.andDo(MockMvcResultHandlers.print());
		actions.andExpect(MockMvcResultMatchers.status().isOk());
		actions.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("50550.0"));

	}
	
	@Test
	void pensionDetailWithInCorrectToken2() throws Exception {
//		PensionerDetail pensionerDetail = new PensionerDetail("123456789", "Lokendra", "12/02/2000", "BUWPTXYZ",
//				50000.0, 10000.0, "self", "ICICI", "9685037015", "private");
//		Mockito.doReturn(pensionerDetail).when(pensionerDetailCleint).pensionerDetailByAadhar(token, "123456789");

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.UNAUTHORIZED);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		mockMvc = standaloneSetup(processPensionController).build();
		PensionerInput input = new PensionerInput("Lokendra", "12/02/2000", "BUWPTXYZ", "123456789", "self");
		ResultActions actions ;
		try {
		actions = mockMvc.perform(MockMvcRequestBuilders.get("/PensionDetail")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(input)).header("Authorization", token));
		}catch (Exception e) {
			assertThrows(TokenException.class, () -> processPensionController.processPension(token,input));

		}
//		actions.andDo(MockMvcResultHandlers.print());
//		actions.andExpect(MockMvcResultMatchers.status().isOk());
//		actions.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("50550.0"));

	}

	@Test
	void pensionDetailWithIncorrectToken() throws TokenException {

		Mockito.doThrow(new TokenException("TOKEN IS NOT CORRECT")).when(cleint).authorization(token + "incorrect");

//       ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
//        Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		mockMvc = standaloneSetup(processPensionController).build();
		PensionerInput input = new PensionerInput("Lokendra", "12/02/2000", "BUWPTXYZ", "123456789", "self");
		ResultActions actions;
		try {
			actions = mockMvc
					.perform(MockMvcRequestBuilders.get("/PensionDetail").contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(input)).header("Authorization", token + "incorrect"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertThrows(TokenException.class, () -> cleint.authorization(token + "incorrect"));
		}

	}

	@Test
	void pensionerDetailByAadharWithIncorrectAadharAndCorrectToken() throws WrongUserException {
		PensionerDetail pensionerDetail = new PensionerDetail("123456789", "Lokendra", "12/02/2000", "BUWPTXYZ",
				50000.0, 10000.0, "self", "ICICI", "9685037015", "private");
		Mockito.doReturn(pensionerDetail).when(pensionerDetailCleint).pensionerDetailByAadhar(token, "123456789");

		ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		Mockito.doReturn(responseEntity).when(cleint).authorization(token);
		mockMvc = standaloneSetup(processPensionController).build();
		PensionerInput input = new PensionerInput("Lokendra", "12/02/2000", "Wrong PAN", "123456789", "self");
		ResultActions actions;
		try {
			actions = mockMvc
					.perform(MockMvcRequestBuilders.get("/PensionDetail").contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(input)).header("Authorization", token));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertThrows(WrongUserException.class, () -> processPensionController.processPension(token, input));

		}

	}
//
//    	
//
//    }

	public static String asJsonString(PensionerInput pensionerInput) {
		try {
			return new ObjectMapper().writeValueAsString(pensionerInput);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String asJsonString(ProcessPensionInput processPensionInput) {
		try {
			return new ObjectMapper().writeValueAsString(processPensionInput);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
