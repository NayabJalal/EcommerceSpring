package com.example.EcommerceSpring;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceSpringApplication {


	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();// load the env variable from dotenv file
		dotenv.entries().forEach( entry -> System.setProperty(entry.getKey(),entry.getValue())); //Get the properties from evn variable and set them

		SpringApplication.run(EcommerceSpringApplication.class, args);
	}

}
