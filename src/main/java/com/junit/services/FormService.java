package com.junit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.models.Form;
import com.junit.repositories.FormRepository;

@Service
public class FormService {
	
	@Autowired
	private FormRepository formRepository;
	
	
	public FormService() 
	{
		super();
	}

	public FormService(FormRepository formRepository) 
	{
		super();
		this.formRepository = formRepository;
	}

	public Form save(Form form)
	{
		return formRepository.save(form);
	}
	
	public boolean existsById(int id) 
	{
		return formRepository.existsById(id);
	}
	
	public List<Form> findAll()
	{
		return formRepository.findAll();
	}

	public Form findFormById(int id) {
		
		Form form = formRepository.findByUID(id);
		
		if(form != null)
		{
			return form;			
		}
		
		return null;
		
	}

}
