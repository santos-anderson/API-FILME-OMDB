package com.example.APIFilmesOMDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiFilmesOmDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFilmesOmDbApplication.class, args);
	}

}
