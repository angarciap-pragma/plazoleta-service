package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.RestaurantNotFoundException;
import com.plazoleta.service.domain.exception.UnauthorizedOwnerException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.port.in.CreateDishUseCase;
import com.plazoleta.service.domain.port.out.DishRepositoryPort;
import com.plazoleta.service.domain.port.out.RestaurantRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateDishService implements CreateDishUseCase {

	private final DishRepositoryPort dishRepositoryPort;
	private final RestaurantRepositoryPort restaurantRepositoryPort;

	public CreateDishService(
			DishRepositoryPort dishRepositoryPort,
			RestaurantRepositoryPort restaurantRepositoryPort) {
		this.dishRepositoryPort = dishRepositoryPort;
		this.restaurantRepositoryPort = restaurantRepositoryPort;
	}

	@Override
	public Dish create(Dish dish, Long ownerId) {
		var restaurant = restaurantRepositoryPort.findById(dish.getRestaurantId())
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurante no encontrado."));
		if (!restaurant.getOwnerId().equals(ownerId)) {
			log.warn("Owner mismatch restaurantId={} ownerId={} requester={}", restaurant.getId(), restaurant.getOwnerId(), ownerId);
			throw new UnauthorizedOwnerException("No tiene permisos para crear platos en este restaurante.");
		}
		Dish toSave = new Dish(
				null,
				dish.getName(),
				dish.getPrice(),
				dish.getDescription(),
				dish.getUrlImage(),
				dish.getCategory(),
				true,
				dish.getRestaurantId());
		return dishRepositoryPort.save(toSave);
	}
}
