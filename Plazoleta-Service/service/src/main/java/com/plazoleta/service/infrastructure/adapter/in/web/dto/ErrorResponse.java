package com.plazoleta.service.infrastructure.adapter.in.web.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponse {

	private int status;
	private String message;
	private OffsetDateTime timestamp;
	private List<FieldErrorResponse> errors;

	public ErrorResponse(int status, String message, OffsetDateTime timestamp, List<FieldErrorResponse> errors) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public List<FieldErrorResponse> getErrors() {
		return errors;
	}
}
