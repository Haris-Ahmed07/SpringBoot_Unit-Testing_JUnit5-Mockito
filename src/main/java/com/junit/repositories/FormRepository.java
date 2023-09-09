package com.junit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.junit.models.Form;

public interface FormRepository extends JpaRepository<Form, Integer>{
	
	@Query("SELECT f FROM Form f WHERE f.uID = :uID")
	public Form findByUID(int uID);

}
