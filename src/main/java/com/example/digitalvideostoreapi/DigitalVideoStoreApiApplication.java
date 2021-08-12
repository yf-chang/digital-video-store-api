package com.example.digitalvideostoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.digitalvideostoreapi", "com.example.digitalvideostoreapi.models", "com.example.digitalvideostoreapi.controllers", "com.example.digitalvideostoreapi.services"})
public class DigitalVideoStoreApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(DigitalVideoStoreApiApplication.class, args);
	}

}
