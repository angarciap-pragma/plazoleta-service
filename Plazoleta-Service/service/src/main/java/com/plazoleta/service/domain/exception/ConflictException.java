package com.plazoleta.service.domain.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
