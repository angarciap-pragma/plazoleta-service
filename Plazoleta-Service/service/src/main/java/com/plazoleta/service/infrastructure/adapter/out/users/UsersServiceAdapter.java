package com.plazoleta.service.infrastructure.adapter.out.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazoleta.service.domain.port.out.UserServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class UsersServiceAdapter implements UserServicePort {

	private final RestClient restClient;
	private final ObjectMapper objectMapper;

	public UsersServiceAdapter(RestClient userServiceRestClient, ObjectMapper objectMapper) {
		this.restClient = userServiceRestClient;
		this.objectMapper = objectMapper;
	}

	@Override
	public String getRoleByUserId(Long userId) {
		try {
			String body = restClient.get()
					.uri("/usuarios/{id}/rol", userId)
					.retrieve()
					.body(String.class);
			return extractRole(body);
		} catch (Exception ex) {
			log.error("Failed to resolve user role userId={}", userId, ex);
			throw new IllegalStateException("No se pudo validar el rol del usuario.");
		}
	}

	private String extractRole(String body) {
		if (body == null) {
			return null;
		}
		String trimmed = body.trim();
		if (trimmed.isEmpty()) {
			return null;
		}
		if (trimmed.startsWith("{")) {
			try {
				JsonNode node = objectMapper.readTree(trimmed);
				for (String field : new String[]{"rol", "role", "nombre", "name"}) {
					JsonNode value = node.get(field);
					if (value != null && value.isTextual()) {
						return value.asText();
					}
				}
			} catch (Exception ex) {
				log.warn("Unable to parse role response body={}", trimmed);
			}
		}
		return trimmed.replace("\"", "");
	}
}
