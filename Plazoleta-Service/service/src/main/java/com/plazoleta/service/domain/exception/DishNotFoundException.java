package com.plazoleta.service.domain.exception;

public class DishNotFoundException extends RuntimeException {

	public DishNotFoundException(String message) {
		super(message);
	}
}
