package com.plazoleta.service.infrastructure.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "plazoleta.usuarios")
public class UsersServiceProperties {

	private String baseUrl;
	private String rolePath;

}
