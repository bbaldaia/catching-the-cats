package com.caseitau.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.caseitau.desafio.model.CatModel;
import com.caseitau.desafio.service.CatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/find-cat")
public class CatController {

	private CatService catService;
	String baseURL = "https://api.thecatapi.com/v1/breeds";
	
	CatController(CatService catService) {
		this.catService = catService;
	}	
	
	private List<CatModel> getCatsFromAPI(String url) throws JsonMappingException, JsonProcessingException {
		
		RestTemplate template = new RestTemplate();		
		String response = template.getForEntity(url, String.class).getBody();
		
		ObjectMapper mapper = new ObjectMapper();		
		List<CatModel> catList = mapper.readValue(response, new TypeReference<List<CatModel>>(){});
		
		return catList;
	}

	
	@GetMapping("/all-cats")
	public String getAllCats() throws JsonMappingException, JsonProcessingException {	
		
		List<CatModel> catList = getCatsFromAPI(baseURL);
		ObjectMapper mapper = new ObjectMapper();
		String catsInJsonFormat = mapper.writeValueAsString(catList);

		return catsInJsonFormat;		
	}
	
	@GetMapping("/breed/{name}")
	public String getByBreed(@PathVariable String name) throws JsonMappingException, JsonProcessingException {
				
		String url = baseURL + "/search?q=" + name;		
		List<CatModel> catList = getCatsFromAPI(url);
			
		CatModel catWithoutArrayFormat = catList.get(0);		
		catService.save(catWithoutArrayFormat);
		
		ObjectMapper mapper = new ObjectMapper();		
		String catInJsonFormat = mapper.writeValueAsString(catWithoutArrayFormat);
		
		return catInJsonFormat;
	}	
	
	@GetMapping("/temperament/{temperament}")
	
	public String getByTemperament(@PathVariable String temperament) throws JsonMappingException, JsonProcessingException {
		
		List<CatModel> catsWithGivenTemperament = new ArrayList<CatModel>();		
		List<CatModel> catList = getCatsFromAPI(baseURL);
		
		for (int i = 0; i < catList.size(); i++) {
			if (catList.get(i).temperament.contains(temperament)) {
				catsWithGivenTemperament.add(catList.get(i));
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String catsInJsonFormat = mapper.writeValueAsString(catsWithGivenTemperament);
		
		return catsInJsonFormat;		
	}

	@GetMapping("/origin/{origin}")
	public String getByOrigin(@PathVariable String origin) throws JsonMappingException, JsonProcessingException {
		
		List<CatModel> catsWithGivenOrigin = new ArrayList<CatModel>();		
		List<CatModel> catList = getCatsFromAPI(baseURL);		
		
		for (int i = 0; i < catList.size(); i++) {
			if (catList.get(i).origin.contains(origin)) {
				catsWithGivenOrigin.add(catList.get(i));
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String catsInJsonFormat = mapper.writeValueAsString(catsWithGivenOrigin);
		
		return catsInJsonFormat;
	}	


}
