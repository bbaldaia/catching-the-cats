package com.caseitau.desafio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.caseitau.desafio.model.Cat;
import com.caseitau.desafio.repository.CatRepository;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {			
		SpringApplication.run(DesafioApplication.class, args);			
	}
	
	@Bean
	 public CommandLineRunner demo(CatRepository repository) {
	    return (args) -> {
			Cat gato1 = new Cat();
			
			gato1.setName("Siberian");
			gato1.setDescription("Pomposo");
			gato1.setOrigin("Europa");
			gato1.setTemperament("Bonzinho");
			
			repository.save(gato1);
	    };
	}
}
