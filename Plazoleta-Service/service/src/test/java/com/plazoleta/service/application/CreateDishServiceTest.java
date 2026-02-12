package com.plazoleta.service.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.plazoleta.service.application.port.out.DishRepositoryPort;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.application.service.CreateDishService;
import com.plazoleta.service.domain.exception.NotFoundException;
import com.plazoleta.service.domain.exception.ForbiddenException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.model.Restaurant;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class CreateDishServiceTest {

	@Test
	void createDishWhenOwnerMatches() {
		// Mocks para aislar la prueba de infraestructura.
		DishRepositoryPort dishRepositoryPort = mock(DishRepositoryPort.class);
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		// Servicio real a probar.
		CreateDishService createDishService = new CreateDishService(dishRepositoryPort, restaurantRepositoryPort);

		// Datos del plato y del restaurante con propietario valido.
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);
		Dish expected = new Dish(1L, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));
		when(dishRepositoryPort.save(any(Dish.class))).thenReturn(expected);

		// Llamada al caso de uso.
		Dish result = createDishService.create(input, 10L);

		// Asercion unica: el resultado debe coincidir.
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void rejectWhenOwnerDoesNotMatch() {
		// Mocks para aislar la prueba de infraestructura.
		DishRepositoryPort dishRepositoryPort = mock(DishRepositoryPort.class);
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		// Servicio real a probar.
		CreateDishService createDishService = new CreateDishService(dishRepositoryPort, restaurantRepositoryPort);

		// Plato y restaurante con propietario distinto.
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));

		// Asercion unica: debe lanzar excepcion.
		assertThatThrownBy(() -> createDishService.create(input, 99L))
				.isInstanceOf(ForbiddenException.class);
	}

	@Test
	void rejectWhenRestaurantMissing() {
		// Mocks para aislar la prueba de infraestructura.
		DishRepositoryPort dishRepositoryPort = mock(DishRepositoryPort.class);
		RestaurantRepositoryPort restaurantRepositoryPort = mock(RestaurantRepositoryPort.class);
		// Servicio real a probar.
		CreateDishService createDishService = new CreateDishService(dishRepositoryPort, restaurantRepositoryPort);

		// Restaurante inexistente.
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.empty());

		// Asercion unica: debe lanzar excepcion.
		assertThatThrownBy(() -> createDishService.create(input, 10L))
				.isInstanceOf(NotFoundException.class);
	}
}


