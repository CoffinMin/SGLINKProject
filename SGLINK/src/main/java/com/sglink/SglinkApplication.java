package com.sglink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SglinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SglinkApplication.class, args);
	}

}
