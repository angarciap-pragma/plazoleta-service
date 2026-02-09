package com.plazoleta.service.infrastructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.plazoleta.service.infrastructure.adapter.in.web.dto.DishCreateRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DishCreateRequestValidationTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void rejectPriceLessThanOne() {
		DishCreateRequest request = validRequest();
		request.setPrice(0);

		var violations = validator.validate(request);

		assertFalse(violations.isEmpty());
	}

	@Test
	void acceptValidRequest() {
		DishCreateRequest request = validRequest();

		var violations = validator.validate(request);

		assertTrue(violations.isEmpty());
	}

	private DishCreateRequest validRequest() {
		DishCreateRequest request = new DishCreateRequest();
		request.setName("Plato 1");
		request.setPrice(12000);
		request.setDescription("Desc");
		request.setUrlImage("http://img");
		request.setCategory("Entrada");
		request.setRestaurantId(5L);
		return request;
	}
}
