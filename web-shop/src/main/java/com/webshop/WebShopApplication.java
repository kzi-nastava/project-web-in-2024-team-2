package com.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

	@Override
	public void run(String... args) {

	}


	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
