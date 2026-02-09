package com.plazoleta.service.infrastructure.adapter.in.web;

import com.plazoleta.service.domain.exception.InvalidOwnerRoleException;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.ErrorResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.FieldErrorResponse;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<FieldErrorResponse> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(this::toFieldError)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Validacion fallida.",
						OffsetDateTime.now(),
						errors));
	}

	@ExceptionHandler(InvalidOwnerRoleException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRole(InvalidOwnerRoleException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						ex.getMessage(),
						OffsetDateTime.now(),
						List.of()));
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleExternalError(IllegalStateException ex) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(new ErrorResponse(
						HttpStatus.BAD_GATEWAY.value(),
						ex.getMessage(),
						OffsetDateTime.now(),
						List.of()));
	}

	private FieldErrorResponse toFieldError(FieldError error) {
		return new FieldErrorResponse(error.getField(), error.getDefaultMessage());
	}
}
