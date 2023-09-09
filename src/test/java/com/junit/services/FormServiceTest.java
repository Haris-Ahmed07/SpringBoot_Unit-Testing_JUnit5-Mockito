package com.junit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.junit.models.Form;
import com.junit.repositories.FormRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FormServiceTest {

	@Mock
	private FormRepository formRepository;
	
	private FormService formService;
	
	
	@BeforeEach
	void setUp() 
	{
		this.formService = new FormService(this.formRepository);
	}

	@Test
	void save()
	{
	    // Temporary Form 
	    Form expectedForm = new Form("Haris","123");
	    
	    // Mock the behavior of the formRepository to define its behavior and compare it with the FormService
	    when(formRepository.save(expectedForm)).thenReturn(expectedForm);
	    when(formRepository.findByUID(expectedForm.getUID())).thenReturn(expectedForm);
	    
	    // Saving The Form In The DB
	    formService.save(expectedForm);
	    
	    // Retrieve the saved Form using the findFormById() method
	    Form actualForm = formService.findFormById(expectedForm.getUID());
	    
	    // Assert that the retrieved Form is equal to the expected Form
	    assertThat(actualForm).isEqualTo(expectedForm);
	}

	@Test
	void existsById()
	{
		// Temporary Form 
		Form expectedForm = new Form("Haris","123");
		
		// Mock the behavior of the formRepository to define its behavior and compare it with the FormService
		when(formRepository.existsById(expectedForm.getUID())).thenReturn(true);
		
		// Saving The Form In The DB 
		formService.save(expectedForm);
		
		// Saving The Result		
		boolean actual = formService.existsById(expectedForm.getUID());
		
		
		// Checking If The User Exists
		assertThat(actual).isTrue();
		
	}

	@Test
	void findAll()
	{
		formService.findAll();
		verify(formRepository).findAll();
	}
	
	@Test
	void findFormById()
	{
		// Temporary Form 
		Form expectedForm = new Form("Haris","123");
				
		// Mock the behavior of the formRepository to define its behavior and compare it with the FormService
		when(formRepository.findByUID(0)).thenReturn(expectedForm);
	
		// Saving The Form In The DB 
		formService.save(expectedForm);
		
		// Retrieve the saved Form using the findFormById() method
		Form actualForm = formService.findFormById(expectedForm.getUID());
		
//		Form
		assertThat(actualForm).isEqualTo(expectedForm);
		
	}
	
}
