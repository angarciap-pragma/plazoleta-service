package com.plazoleta.service.infrastructure.adapter.out.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazoleta.service.application.port.out.UserServicePort;
import com.plazoleta.service.infrastructure.config.properties.UsersServiceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class UsersServiceAdapter implements UserServicePort {

	// Adaptador que consulta el microservicio de usuarios y resuelve el rol del usuario.
	private static final String MSG_NO_SE_PUDO_VALIDAR_ROL = "No se pudo validar el rol del usuario.";
	private static final String JSON_OBJECT_PREFIX = "{";
	private static final String[] ROLE_FIELDS = {"rol", "role", "nombre", "name"};

	private final RestClient restClient;
	private final ObjectMapper objectMapper;
	private final UsersServiceProperties properties;

	@Override
	public String getRoleByUserId(Long userId) {
		try {
			log.info("Consuming users service to fetch user role. userId={}", userId);
			String body = restClient.get()
					.uri(properties.getRolePath(), userId)
					.retrieve()
					.body(String.class);
			String role = extractRole(body);
			log.info("User role resolved from users service. userId={} role={}", userId, role);
			return role;
		} catch (Exception ex) {
			log.error("Failed to fetch user role from users service. userId={}", userId, ex);
			throw new IllegalStateException(MSG_NO_SE_PUDO_VALIDAR_ROL);
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
		if (trimmed.startsWith(JSON_OBJECT_PREFIX)) {
			try {
				JsonNode node = objectMapper.readTree(trimmed);
				for (String field : ROLE_FIELDS) {
					JsonNode value = node.get(field);
					if (value != null && value.isTextual()) {
						return value.asText();
					}
				}
			} catch (Exception _) {
				log.warn("Failed to parse role from users service response. body={}", trimmed);
			}
		}
		return trimmed.replace("\"", "");
	}
}
