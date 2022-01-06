package com.caseitau.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.CatModel;
import com.caseitau.desafio.repository.CatRepository;
import com.caseitau.desafio.service.CatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/find-cat")
public class CatController {

	private CatService catService;
	
	CatController(CatService catService) {
		this.catService = catService;
	}
	
	String baseURL = "https://api.thecatapi.com/v1/breeds";
	
	private List<CatModel> getCatsFromAPI(String url) throws JsonMappingException, JsonProcessingException {
		RestTemplate template = new RestTemplate();
		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<CatModel> catList = mapper.readValue(response, new TypeReference<List<CatModel>>(){});
		
		return catList;
	}
	
	@GetMapping("/allcats")
	public String getAllCats() throws JsonMappingException, JsonProcessingException {	
		List<CatModel> cats = getCatsFromAPI(baseURL);
		
		return cats.toString();		
	}

	
	@GetMapping("/temper/{temper}")
	//get URL from Postman
	public String getByTemper() throws JsonMappingException, JsonProcessingException {
		
		List<String> gentleCats = new ArrayList<String>();
		
		List<CatModel> cats = getCatsFromAPI(baseURL);
		
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).temperament.contains("Gentle")) {
				gentleCats.add(cats.get(i).toString());
			}
		}
		
		return gentleCats.toString();		
	}

	@GetMapping("/origin/{origin}")
	//get URL from Postman
	public String getByOrigin() throws JsonMappingException, JsonProcessingException {
		
		List<String> frenchCats = new ArrayList<String>();
		
		List<CatModel> cats = getCatsFromAPI(baseURL);
		
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).origin.contains("France")) {
				frenchCats.add(cats.get(i).toString());
			}
		}
		
		return frenchCats.toString();	
	}	
	
	@GetMapping("/breed/{name}")
	public String GetByBreed(@PathVariable String name) throws JsonMappingException, JsonProcessingException {
				
		String url = baseURL + "/search?q=" + name;
		
		List<CatModel> catList = getCatsFromAPI(url);

		CatModel cat = catList.get(0);
		
		catService.save(cat);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String catJson = mapper.writeValueAsString(cat);
		
		return catJson;
	}	
}


