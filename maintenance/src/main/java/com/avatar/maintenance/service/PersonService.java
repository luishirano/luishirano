package com.avatar.maintenance.service;

import java.util.List;

import com.avatar.maintenance.dto.Person;


public interface PersonService {

	public int save(Person person);

	public int delete(Integer id);

	public List<Person> lista();

	
	
	

}
