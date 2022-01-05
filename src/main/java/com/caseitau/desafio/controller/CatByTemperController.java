package com.caseitau.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.CatByTemper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/gentle-temper-cats")
public class CatByTemperController {
	
	@GetMapping
	public String getByTemper() throws JsonMappingException, JsonProcessingException {
		
		List<String> gentleCats = new ArrayList<String>();
		
		RestTemplate template = new RestTemplate();
		
		String url = "https://api.thecatapi.com/v1/breeds";
		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<CatByTemper> cats = mapper.readValue(response, new TypeReference<List<CatByTemper>>(){});
		
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).temperament.contains("Gentle")) {
				gentleCats.add(cats.get(i).toString());
			}
		}
		
		return gentleCats.toString();		
	}
}
