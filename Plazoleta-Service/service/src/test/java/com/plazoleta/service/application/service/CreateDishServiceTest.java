package com.plazoleta.service.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.plazoleta.service.domain.exception.RestaurantNotFoundException;
import com.plazoleta.service.domain.exception.UnauthorizedOwnerException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.domain.port.out.DishRepositoryPort;
import com.plazoleta.service.domain.port.out.RestaurantRepositoryPort;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateDishServiceTest {

	@Mock
	private DishRepositoryPort dishRepositoryPort;

	@Mock
	private RestaurantRepositoryPort restaurantRepositoryPort;

	@InjectMocks
	private CreateDishService createDishService;

	@Test
	void createDishWhenOwnerMatches() {
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Dish saved = new Dish(1L, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));
		when(dishRepositoryPort.save(any())).thenReturn(saved);

		Dish result = createDishService.create(input, 10L);

		assertEquals(1L, result.getId());
		verify(dishRepositoryPort).save(any());
	}

	@Test
	void rejectWhenOwnerDoesNotMatch() {
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));

		assertThrows(UnauthorizedOwnerException.class, () -> createDishService.create(input, 99L));
	}

	@Test
	void rejectWhenRestaurantMissing() {
		Dish input = new Dish(null, "Plato 1", 12000, "Desc", "http://img", "Entrada", true, 5L);

		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.empty());

		assertThrows(RestaurantNotFoundException.class, () -> createDishService.create(input, 10L));
	}
}
