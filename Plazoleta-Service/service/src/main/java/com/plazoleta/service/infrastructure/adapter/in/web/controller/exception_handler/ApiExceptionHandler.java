package com.plazoleta.service.infrastructure.adapter.in.web.controller.exception_handler;

import com.plazoleta.service.domain.exception.ApiException;
import java.time.OffsetDateTime;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	private static final String VALIDATION_FAILED = "Validacion fallida.";
	private static final String INVALID_JSON = "JSON invalido en la solicitud.";
	private static final String NOT_FOUND = "Ruta no encontrada.";
	private static final String UNEXPECTED_ERROR = "Error interno del servidor.";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		Map<String, String> fieldErrors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
		log.warn("validation.failed path={} errors={}", request.getRequestURI(), fieldErrors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorWithDetails(VALIDATION_FAILED, HttpStatus.BAD_REQUEST, request, fieldErrors));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(
			IllegalArgumentException ex,
			HttpServletRequest request) {
		log.warn("request.rejected path={} message={}", request.getRequestURI(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorOnly(ex.getMessage(), HttpStatus.BAD_REQUEST, request));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleNotReadable(
			HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		log.warn("request.invalid_json path={}", request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorOnly(INVALID_JSON, HttpStatus.BAD_REQUEST, request));
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoResource(
			NoResourceFoundException ex,
			HttpServletRequest request) {
		log.warn("request.no_resource path={} message={}", request.getRequestURI(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(errorOnly(NOT_FOUND, HttpStatus.NOT_FOUND, request));
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleApiException(
			ApiException ex,
			HttpServletRequest request) {
		log.warn("request.failed path={} message={}", request.getRequestURI(), ex.getMessage());
		return ResponseEntity.status(ex.getStatus())
				.body(errorOnly(ex.getMessage(), ex.getStatus(), request));
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleExternalError(
			IllegalStateException ex,
			HttpServletRequest request) {
		log.warn("external.error path={} message={}", request.getRequestURI(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(errorOnly(ex.getMessage(), HttpStatus.BAD_GATEWAY, request));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnexpected(
			Exception ex,
			HttpServletRequest request) {
		log.error("unexpected.error type={} path={}", ex.getClass().getSimpleName(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorOnly(UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, request));
	}

	private ErrorResponse errorOnly(String message, HttpStatus status, HttpServletRequest request) {
		return baseBody(message, status, request, null);
	}

	private ErrorResponse errorWithDetails(
			String message,
			HttpStatus status,
			HttpServletRequest request,
			Map<String, String> details) {
		return baseBody(message, status, request, details);
	}

	private ErrorResponse baseBody(
			String message,
			HttpStatus status,
			HttpServletRequest request,
			Map<String, String> details) {
		return new ErrorResponse(
				message,
				status.value(),
				OffsetDateTime.now(),
				request.getRequestURI(),
				details);
	}
}
