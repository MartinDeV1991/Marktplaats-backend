package com.devteam.marktplaats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.youngcapital.marktplaats")
public class MarktplaatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarktplaatsApplication.class, args);
	}

}
