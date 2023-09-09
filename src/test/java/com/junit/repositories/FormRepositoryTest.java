package com.junit.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.junit.models.Form;

@SpringBootTest
public class FormRepositoryTest {
	
	@Autowired
	private FormRepository formRepository;

	@Test
	void findByUID()
	{
//		Creating Temporary Form
		Form expectedFrom = new Form("Haris","123");
		
//		Inserting This Form Into The DB
		formRepository.save(expectedFrom);
		
		Form actualForm = formRepository.findByUID(expectedFrom.getUID());
		
//		Deleting The Temporary Form From DB
		formRepository.delete(expectedFrom);
		
//		Checking if the test passes or fails.
		assertThat(actualForm.getUsername()).isEqualTo(expectedFrom.getUsername());
		
	}
	
}
