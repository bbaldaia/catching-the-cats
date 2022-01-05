package com.caseitau.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatByTemper {
	
	public String name;
	public String temperament;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemperament() {
		return temperament;
	}
	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}
	@Override
	public String toString() {
		return "Name: " + name + ", temperament: " + temperament;
	}	
	
}
