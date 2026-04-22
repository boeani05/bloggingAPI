package com.boeani.bloggingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application bootstrap class for the Blogging API.
 */
@SpringBootApplication
public class BloggingApiApplication {

	/**
	 * Starts the Spring Boot application context.
	 *
	 * @param args command-line arguments passed at startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(BloggingApiApplication.class, args);
	}

}
