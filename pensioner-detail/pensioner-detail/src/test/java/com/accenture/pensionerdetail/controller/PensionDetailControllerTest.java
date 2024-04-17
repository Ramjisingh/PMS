package com.accenture.pensionerdetail.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accenture.pensionerdetail.client.MicroserviceClient;
import com.accenture.pensionerdetail.exception.TokenException;
import com.accenture.pensionerdetail.exception.UserNotFound;
import com.accenture.pensionerdetail.model.PensionerDetail;
import com.accenture.pensionerdetail.service.PensionerDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class PensionDetailControllerTest {

    @Mock
    private PensionerDetailService userService;
    @Mock
    private MicroserviceClient cleint;

    @InjectMocks
    private PensionDetailController userController;

    private MockMvc mockMvc;

	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMb2tlbmRyYSIsImV4cCI6MTcxMjYyODg2MywiaWF0IjoxNzEyNTkyODYzfQ.nD3VPLEQqbPGzjKI-EKtOk-Lk88Ia0FKcwJOaJiGxFQ";

    @Test
    void  pensionerDetailByAadharWithCorrectAadharAndCorrectToken() throws Exception {
        when(userService.getDetailByAadhar("123456789")).thenReturn(new PensionerDetail("123456789","Lokendra","12/02/2000","BUWPTXYZ",50000.0,10000.0,"self","ICICI","9685037015","private"));
       ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
       
        Mockito.doReturn(responseEntity).when(cleint).authorization(token);
        mockMvc = standaloneSetup(userController).build();

        ResultActions actions =  mockMvc.perform(MockMvcRequestBuilders.get("/PensionerDetailByAadhaar").param("aadhar", "123456789")
		.header("Authorization", token));
        actions.andDo(MockMvcResultHandlers.print());
        actions.andExpect(MockMvcResultMatchers.status().isOk());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.aadhaarNumber").value("123456789"));
    	
    	

    }
    
    @Test
    void  pensionerDetailByAadharWithCorrectAadharAndIncorrectToken() {
//        when(userService.getDetailByAadhar("123456789")).thenReturn(new PensionerDetail("123456789","Lokendra","12/02/2000","BUWPTXYZ",50000.0,10000.0,"self","ICICI","9685037015","private"));
       ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
       
        Mockito.doThrow(new TokenException("INVALID TOKEN")).when(cleint).authorization(token+"incorrect");
        mockMvc = standaloneSetup(userController).build();

        ResultActions actions;
		try {
			actions = mockMvc.perform(MockMvcRequestBuilders.get("/PensionerDetailByAadhaar").param("aadhar", "123456789")
			.header("Authorization", token+"incorrect"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
	        assertThrows(TokenException.class, () -> cleint.authorization(token+"incorrect"));
		}
//        actions.andDo(MockMvcResultHandlers.print());
//        actions.andExpect(MockMvcResultMatchers.);
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.aadhaarNumber").value("123456789"));
    	
    	

    }
    
    @Test
    void  pensionerDetailByAadharWithIncorrectAadharAndCorrectToken() throws Exception {
        when(userService.getDetailByAadhar("123456")).thenThrow(new UserNotFound("User with this aadhar card not exist"));
       ResponseEntity<?> responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
       
        Mockito.doReturn(responseEntity).when(cleint).authorization(token);
        mockMvc = standaloneSetup(userController).build();

        try {
        ResultActions actions =  mockMvc.perform(MockMvcRequestBuilders.get("/PensionerDetailByAadhaar").param("aadhar", "123456")
		.header("Authorization", token));
        }catch (Exception e) {
	        assertThrows(UserNotFound.class, () -> userService.getDetailByAadhar("123456"));

		}
//        actions.andDo(MockMvcResultHandlers.print());
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//        actions.andExpect(MockMvcResultMatchers.jsonPath("$.aadhaarNumber").value("123456789"));
    	
    	

    }



    
    
}