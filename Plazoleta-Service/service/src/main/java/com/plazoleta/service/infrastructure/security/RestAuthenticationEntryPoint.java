package com.plazoleta.service.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.ErrorResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// Responde 401 cuando el usuario no esta autenticado o el token es invalido/expirado.
	private final ObjectMapper objectMapper;
	private static final String USER_NOT_AUTHENTICATED = "No autenticado.";

	@Override
	public void commence(
			jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		log.warn("auth.unauthorized path={} method={}", request.getRequestURI(), request.getMethod());
		String message = authException != null && authException.getMessage() != null
				? authException.getMessage()
				: USER_NOT_AUTHENTICATED;
		ErrorResponse body = new ErrorResponse(
				message,
				HttpStatus.UNAUTHORIZED.value(),
				OffsetDateTime.now(),
				request.getRequestURI(),
				null);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getOutputStream(), body);
	}
}
