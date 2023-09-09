package com.junit.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.models.Form;
import com.junit.services.FormService;

@WebMvcTest(FormController.class)
public class FormControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FormService formService;

	@Test
	void submit() throws Exception
	{
	    // Temporary Form
	    Form form = new Form(82,"Haris","123");
	    
	    // Mock the behavior of the formService to define its behavior and compare it with the FormService
	    when(formService.save(form)).thenReturn(form);
	    
	    // ExpectedResponse From The Controller
	    String expectedResponse = "Form Submitted Successfully";
	    
	    // Perform a POST request to the /submit endpoint with form data
	    // This is to test if the endpoint can handle requests with form data correctly	    
	    mockMvc.perform(MockMvcRequestBuilders.post("/submit")
	            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .param("username", "Haris")
	            .param("password", "123")
	            .param("uid", "82"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
	    
	    // Perform a POST request to the /submit endpoint with a JSON object
	    // This is to test if the endpoint can handle requests with a JSON object correctly	    
//	    mockMvc.perform(MockMvcRequestBuilders.post("/submit")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content("{\r\n"
//	                    + "    \"username\": \"Haris\",\r\n"
//	                    + "    \"password\": \"123\",\r\n"
//	                    + "    \"uid\": 82\r\n"
//	                    + "}"))
//	            .andExpect(MockMvcResultMatchers.status().isOk())
//	            .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
	}
	
	
	@Test
	void findFormById() throws Exception
	{
		// Temporary Form
		Form form = new Form(82,"Haris","123");
		
		// Mock the behavior of the formRepository to define its behavior and compare it with the FormService
		when(formService.findFormById(82)).thenReturn(form);
		
		// Using This Method To Check And Compare the JSON Object & Check The HTTP Status
//		mockMvc.perform(MockMvcRequestBuilders.get("/form/82"))
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().json("{\r\n"
//                + "    \"username\": \"Haris\",\r\n"
//                + "    \"password\": \"123\",\r\n"
//                + "    \"uid\": 82\r\n"
//                + "}"));
		
		// Using This Method To Check The HTTP Status Only
		mockMvc.perform(MockMvcRequestBuilders.get("/form/82"))
        .andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void forms() throws JsonProcessingException, Exception 
	{
		// Temporary List Object		
		List<Form> forms = new ArrayList<Form>();
		
		forms.add(new Form(82,"Haris","123"));
		forms.add(new Form(83,"Ahmed","123"));
		
		// Mock the behavior of the formService's findAll method to return the list of forms when it is called
		when(formService.findAll()).thenReturn(forms);
		
		
		// Perform a GET request to the /forms endpoint
	    mockMvc.perform(MockMvcRequestBuilders.get("/forms"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(forms)));
		
		
		
	}
	
}
