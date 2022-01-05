package com.caseitau.desafio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.BaliCat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/balinese-cat")
public class BaliCatController {
	
	@GetMapping
	public String getBalineseProps() throws JsonMappingException, JsonProcessingException {	
		
		RestTemplate template = new RestTemplate();
		
		String url = "https://api.thecatapi.com/v1/breeds/search?q=sib";
		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<BaliCat> baliCat = mapper.readValue(response, new TypeReference<List<BaliCat>>(){});
		
		return baliCat.toString();
	}

}
