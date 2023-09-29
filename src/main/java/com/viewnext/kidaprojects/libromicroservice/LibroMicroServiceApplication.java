package com.viewnext.kidaprojects.libromicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.viewnext.kidaprojects.libromicroservice.model")
@EnableJpaRepositories(basePackages = "com.viewnext.kidaprojects.libromicroservice.repository")
@SpringBootApplication(scanBasePackages = {"com.viewnext.kidaprojects.libromicroservice.service", 
		"com.viewnext.kidaprojects.libromicroservice.restcontroller"})
public class LibroMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibroMicroServiceApplication.class, args);
	}

}
