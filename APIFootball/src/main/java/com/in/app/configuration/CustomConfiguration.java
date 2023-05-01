package com.in.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfiguration {

	public static RestTemplate restTemplate = null;

	@Bean
	public RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
			return restTemplate;
		} else {
			return restTemplate;
		}
	}
}
