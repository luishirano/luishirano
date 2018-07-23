package com.avatar.maintenance.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.avatar.maintenance.dto.Person;
import com.avatar.maintenance.repository.PersonRepository;
import com.avatar.maintenance.service.PersonService;


@Service("personService")
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonRepository pRepository;
	
	@Override
	public int save(Person person) {
		int resultado = 0; 
		 	RestTemplate rt = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        String url = "https://swapi.co/api/planets/";
	        ResponseEntity<String> res = rt.exchange(url, HttpMethod.GET, entity, String.class);
	        JSONObject json;
			try {
				json = new JSONObject(res.getBody());				
		       if(json.getJSONArray("results").getJSONObject(0).getString("name").equals(person.getPlanet().toString())){
		    	   pRepository.save(person); 
		    	 
		    	   resultado = 1;
		       }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;	
	}
		
	
	@Override
	public int delete(Integer id) {
		int result = 0;
		Optional<Person> person = pRepository.findById(id);
		if (person!=null) {
			pRepository.deleteById(id);
			result = 1;		
		} 
		return result;
	}
	
	@Override
	public List<Person> lista(){
		List<Person> listPerson = (List<Person>) pRepository.findAll();
	return listPerson;
	}
	
	

	


	
}
