package com.dng.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class DngTrainingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DngTrainingApiApplication.class, args);
	}
}