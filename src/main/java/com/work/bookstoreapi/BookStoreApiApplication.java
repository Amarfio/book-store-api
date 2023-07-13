package com.work.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//developer Joshua Amarfio
// date started 13th July 2023

@SpringBootApplication
public class BookStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**");
			}
		};
	}
}
