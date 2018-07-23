package com.avatar.maintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avatar.maintenance.dto.Person;
import com.avatar.maintenance.service.PersonService;

@RestController
public class PersonController {


	@Autowired
	PersonService pService;


	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> create(@RequestBody Person person) {
		int result = pService.save(person);
		if(result == 1) {
		return ResponseEntity.status(HttpStatus.OK).body("El registro de realizó correctamente");
	}else {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El nombre de planeta ingresado no existe");
	}
}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody Integer id) {
		pService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Person> list() {		
		List<Person> listPerson = pService.lista();			
		return listPerson;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> update(@PathVariable Integer id, Person person) {
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}