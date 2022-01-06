package com.caseitau.desafio.service;

import org.springframework.stereotype.Service;

import com.caseitau.desafio.model.CatModel;
import com.caseitau.desafio.repository.CatRepository;

@Service
public class CatService {	
	private CatRepository catRepository;

	public CatService(CatRepository catRepository) {
		this.catRepository = catRepository;
	}
	
	public CatModel save(CatModel cat) {
		return catRepository.save(cat);
	}	
	
}
