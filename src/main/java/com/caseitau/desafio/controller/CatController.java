package com.caseitau.desafio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/allbreeds")
public class CatController {

	@GetMapping
	public String getAllBreeds() throws JsonMappingException, JsonProcessingException {
		
		RestTemplate template = new RestTemplate();
		
		String url = "https://api.thecatapi.com/v1/breeds";
		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<Cat> cats = mapper.readValue(response, new TypeReference<List<Cat>>(){});
		
		return cats.toString();		
	}	
	
}
