package com.plazoleta.service.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.plazoleta.service.domain.exception.DishNotFoundException;
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
class UpdateDishServiceTest {

	@Mock
	private DishRepositoryPort dishRepositoryPort;

	@Mock
	private RestaurantRepositoryPort restaurantRepositoryPort;

	@InjectMocks
	private UpdateDishService updateDishService;

	@Test
	void updateDishWhenOwnerMatches() {
		Dish existing = new Dish(2L, "Plato", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Dish saved = new Dish(2L, "Plato", 15000, "Nueva", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);

		when(dishRepositoryPort.findById(2L)).thenReturn(Optional.of(existing));
		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));
		when(dishRepositoryPort.save(any())).thenReturn(saved);

		Dish result = updateDishService.update(2L, 15000, "Nueva", 10L);

		assertEquals(15000, result.getPrice());
		verify(dishRepositoryPort).save(any());
	}

	@Test
	void rejectWhenDishMissing() {
		when(dishRepositoryPort.findById(2L)).thenReturn(Optional.empty());

		assertThrows(DishNotFoundException.class, () -> updateDishService.update(2L, 15000, "Nueva", 10L));
	}

	@Test
	void rejectWhenOwnerDoesNotMatch() {
		Dish existing = new Dish(2L, "Plato", 12000, "Desc", "http://img", "Entrada", true, 5L);
		Restaurant restaurant = new Restaurant(5L, "Rest", "123", "Dir", "300", "http://logo", 10L);

		when(dishRepositoryPort.findById(2L)).thenReturn(Optional.of(existing));
		when(restaurantRepositoryPort.findById(5L)).thenReturn(Optional.of(restaurant));

		assertThrows(UnauthorizedOwnerException.class, () -> updateDishService.update(2L, 15000, "Nueva", 99L));
	}
}
