package com.plazoleta.service.infrastructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.plazoleta.service.infrastructure.adapter.in.web.dto.RestaurantCreateRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantCreateRequestValidationTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void rejectNameWithOnlyNumbers() {
		RestaurantCreateRequest request = validRequest();
		request.setName("123456");

		var violations = validator.validate(request);

		assertFalse(violations.isEmpty());
	}

	@Test
	void acceptPhoneWithPlusAndMaxLength() {
		RestaurantCreateRequest request = validRequest();
		request.setPhone("+573005698325");

		var violations = validator.validate(request);

		assertTrue(violations.isEmpty());
	}

	private RestaurantCreateRequest validRequest() {
		RestaurantCreateRequest request = new RestaurantCreateRequest();
		request.setName("Restaurante 123");
		request.setNit("123456");
		request.setAddress("Calle 1");
		request.setPhone("3005698325");
		request.setUrlLogo("http://logo");
		request.setOwnerId(1L);
		return request;
	}
}
