package com.plazoleta.service.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.ErrorResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	// Responde 403 cuando el usuario esta autenticado pero no tiene permisos.
	private final ObjectMapper objectMapper;
	private static final String ACCESS_DENIED = "No tiene permisos para acceder a este recurso.";

	@Override
	public void handle(
			jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		log.warn("auth.forbidden path={} method={}", request.getRequestURI(), request.getMethod());
		ErrorResponse body = new ErrorResponse(
				ACCESS_DENIED,
				HttpStatus.FORBIDDEN.value(),
				OffsetDateTime.now(),
				request.getRequestURI(),
				null);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getOutputStream(), body);
	}
}
