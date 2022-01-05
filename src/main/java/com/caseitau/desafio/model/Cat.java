package com.caseitau.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Cat")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "temperament", length = 50, nullable = false)
	private String temperament;
	
	@Column(name = "origin", length = 50, nullable = false)
	private String origin;
	
	@Column(name = "description", length = 50, nullable = false)
	private String description;	
	
	public Cat() {
		
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}

	public String getTemperament() {
		return temperament;
	}
	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", temperament=" + temperament + ", origin=" + origin
				+ ", description=" + description;
	}
	
	
}
