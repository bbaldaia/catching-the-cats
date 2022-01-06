package com.caseitau.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseitau.desafio.model.CatModel;

@Repository
public interface CatRepository extends JpaRepository<CatModel, Long> {
	public CatModel findByName(String name);
}
