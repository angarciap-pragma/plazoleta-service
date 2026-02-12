package com.plazoleta.service.infrastructure.config;

import com.plazoleta.service.infrastructure.config.properties.UsersServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UsersServiceClientConfig {

	@Bean
	public RestClient userServiceRestClient(UsersServiceProperties properties) {
		return RestClient.builder()
				.baseUrl(properties.getBaseUrl())
				.build();
	}
}
