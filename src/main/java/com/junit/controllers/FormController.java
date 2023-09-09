package com.junit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.junit.models.Form;
import com.junit.services.FormService;


@Controller
public class FormController {
	
	@Autowired
	private FormService formService;
	
	
	@GetMapping("/form")
	public String login() 
	{
		return "form";
	}
	
	@ResponseBody
	@PostMapping("/submit")
	public ResponseEntity<?> submit(@ModelAttribute("form") Form form) throws NullPointerException
	{
		System.out.println(form);
		if (form !=null) 
		{			
			if (!(form.getUsername().isBlank() || form.getPassword().isBlank())) 
			{
				if (!formService.existsById(form.getUID())) 
				{
					formService.save(form);
					return ResponseEntity.status(HttpStatus.OK).body("Form Submitted Successfully");
				}
			}
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something Went Wrong");
		
	}

	@GetMapping("/form/{id}")
	public ResponseEntity<?> findFormById(@PathVariable("id") Integer id)
	{
		Form form = formService.findFormById(id);
		if(form != null)
		{
			return ResponseEntity.ok(form);			
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Form Not Found");
		}
	}

	@GetMapping("/forms")
	public ResponseEntity<?> Forms()
	{
		return ResponseEntity.ok(formService.findAll());
	}


}


