package com.plazoleta.service.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.plazoleta.service.domain.exception.InvalidOwnerRoleException;
import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.domain.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.domain.port.out.UserServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantServiceTest {

	@Mock
	private RestaurantRepositoryPort restaurantRepositoryPort;

	@Mock
	private UserServicePort userServicePort;

	@InjectMocks
	private CreateRestaurantService createRestaurantService;

	@Test
	void createRestaurantWhenOwnerRoleIsValid() {
		Restaurant input = new Restaurant(null, "Restaurante 123", "123456", "Calle 1", "+573005698325", "http://logo", 10L);
		Restaurant saved = new Restaurant(1L, "Restaurante 123", "123456", "Calle 1", "+573005698325", "http://logo", 10L);

		when(userServicePort.getRoleByUserId(10L)).thenReturn("PROPIETARIO");
		when(restaurantRepositoryPort.save(input)).thenReturn(saved);

		Restaurant result = createRestaurantService.create(input);

		assertEquals(1L, result.getId());
		verify(restaurantRepositoryPort).save(input);
	}

	@Test
	void rejectWhenOwnerRoleIsInvalid() {
		Restaurant input = new Restaurant(null, "Restaurante 123", "123456", "Calle 1", "3005698325", "http://logo", 99L);

		when(userServicePort.getRoleByUserId(99L)).thenReturn("CLIENTE");

		assertThrows(InvalidOwnerRoleException.class, () -> createRestaurantService.create(input));
	}
}
