package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DemoApplication {
	@Configuration
	public class AppConfiguration {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}
