package com.plazoleta.service.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.application.port.out.UserServicePort;
import com.plazoleta.service.application.service.CreateRestaurantService;
import com.plazoleta.service.domain.exception.ForbiddenException;
import com.plazoleta.service.domain.model.Restaurant;
import org.junit.jupiter.api.Test;

class CreateRestaurantServiceTest {

	@Test
	void createRestaurantWhenOwnerRoleIsValid() {
		// Mocks para aislar la prueba de infraestructura.
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		UserServicePort userServicePort = mock(UserServicePort.class);
		// Servicio real a probar.
		CreateRestaurantService createRestaurantService = new CreateRestaurantService(restaurantRepositoryPort, userServicePort);

		// Rol valido con espacios y en minusculas.
		Restaurant input =
				new Restaurant(null, "Restaurante 123", "123456", "Calle 1", "+573005698325", "http://logo", 10L);
		Restaurant expected =
				new Restaurant(1L, "Restaurante 123", "123456", "Calle 1", "+573005698325", "http://logo", 10L);

		when(userServicePort.getRoleByUserId(10L)).thenReturn(" propietario ");
		when(restaurantRepositoryPort.save(input)).thenReturn(expected);

		// Llamada al caso de uso.
		Restaurant result = createRestaurantService.create(input);

		// Asercion unica: el resultado debe coincidir.
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void rejectWhenOwnerRoleIsInvalid() {
		// Mocks para aislar la prueba de infraestructura.
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		UserServicePort userServicePort = mock(UserServicePort.class);
		// Servicio real a probar.
		CreateRestaurantService createRestaurantService = new CreateRestaurantService(restaurantRepositoryPort, userServicePort);

		// Rol distinto al permitido.
		Restaurant input = new Restaurant(null, "Restaurante 123", "123456", "Calle 1", "3005698325", "http://logo", 99L);

		when(userServicePort.getRoleByUserId(99L)).thenReturn("CLIENTE");

		// Asercion unica: debe lanzar excepcion.
		assertThatThrownBy(() -> createRestaurantService.create(input))
				.isInstanceOf(ForbiddenException.class);
	}

	@Test
	void rejectWhenOwnerRoleIsNull() {
		// Mocks para aislar la prueba de infraestructura.
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		UserServicePort userServicePort = mock(UserServicePort.class);
		// Servicio real a probar.
		CreateRestaurantService createRestaurantService = new CreateRestaurantService(restaurantRepositoryPort, userServicePort);

		// Rol nulo desde el servicio de usuarios.
		Restaurant input = new Restaurant(null, "Restaurante 123", "123456", "Calle 1", "3005698325", "http://logo", 88L);

		when(userServicePort.getRoleByUserId(88L)).thenReturn(null);

		// Asercion unica: debe lanzar excepcion.
		assertThatThrownBy(() -> createRestaurantService.create(input))
				.isInstanceOf(ForbiddenException.class);
	}
}
