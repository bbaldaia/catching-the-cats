package com.caseitau.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Cat")
public class CatModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long databaseid;
	
	@Column(name = "cat_id", length = 255)
	public String id;
	
	@Column(name = "name", length = 255)
	public String name;
	
	@Column(name = "temperament", length = 255)
	public String temperament;
	
	@Column(name = "origin", length = 255)
	public String origin;
	
	@Column(name = "description", length = 255)
	public String description;		
	
	public CatModel() {
		
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getDatabaseid() {
		return databaseid;
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
