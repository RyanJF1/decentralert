package com.crypt.decentralert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DecentralertApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecentralertApplication.class, args);
	}

}
