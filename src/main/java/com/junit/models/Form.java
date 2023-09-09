package com.junit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Form {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int uID;
	
	private String username;
	private String password;
	
	public Form(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	
}