package com.caseitau.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.CatByOrigin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/french-cats")
public class CatByOriginController {
	
	@GetMapping
	public String getByOrigin() throws JsonMappingException, JsonProcessingException {
		
		List<String> frenchCats = new ArrayList<String>();
		
		RestTemplate template = new RestTemplate();
		
		String url = "https://api.thecatapi.com/v1/breeds";
		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<CatByOrigin> cats = mapper.readValue(response, new TypeReference<List<CatByOrigin>>(){});
		
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).origin.contains("France")) {
				frenchCats.add(cats.get(i).toString());
			}
		}
		
		return frenchCats.toString();	
	}	
}
