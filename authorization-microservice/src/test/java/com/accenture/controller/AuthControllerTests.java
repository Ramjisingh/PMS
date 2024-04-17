package com.accenture.controller;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.accenture.controller.AuthorizationController;
import com.accenture.model.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = AuthorizationController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AuthorizationController authorizationController;

	@Test
	public void contextLoads() {
		assertNotNull(authorizationController);
	}

	@Test
	public void loginTestSuccess() throws Exception {
		AuthRequest admin = new AuthRequest("Lokendra", "Lokendr@012");

		ResultActions actions = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isOk());
	}

	@Test
	public void loginTestFail() throws Exception {
		AuthRequest admin = new AuthRequest("WrongUser", "password1");

		ResultActions actions = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isNotFound());
	}

	public static String asJsonString(AuthRequest admin) {
		try {
			return new ObjectMapper().writeValueAsString(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}