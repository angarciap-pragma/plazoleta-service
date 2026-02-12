package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.NotFoundException;
import com.plazoleta.service.domain.exception.ForbiddenException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.application.port.in.UpdateDishUseCase;
import com.plazoleta.service.application.port.out.DishRepositoryPort;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateDishService implements UpdateDishUseCase {

	private static final String DISH_NOT_FOUND = "Plato no encontrado.";
	private static final String RESTAURANT_NOT_FOUND_FOR_DISH = "Restaurante no encontrado para el plato.";
	private static final String NO_PERMISSION_TO_UPDATE_DISH = "No tiene permisos para modificar este plato.";

	private final DishRepositoryPort dishRepositoryPort;
	private final RestaurantRepositoryPort restaurantRepositoryPort;

	public UpdateDishService(
			DishRepositoryPort dishRepositoryPort,
			RestaurantRepositoryPort restaurantRepositoryPort) {
		this.dishRepositoryPort = dishRepositoryPort;
		this.restaurantRepositoryPort = restaurantRepositoryPort;
	}

	@Override
	public Dish update(Long dishId, Integer price, String description, Long ownerId) {
		log.info("dish.update.request dishId={} requesterId={}", dishId, ownerId);
		Dish existing = dishRepositoryPort.findById(dishId)
				.orElseThrow(() -> new NotFoundException(DISH_NOT_FOUND));
		var restaurant = restaurantRepositoryPort.findById(existing.restaurantId())
				.orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND_FOR_DISH));
		if (!restaurant.ownerId().equals(ownerId)) {
			log.warn("Owner mismatch for dishId={} restaurantId={} ownerId={} requester={}",
					dishId, restaurant.id(), restaurant.ownerId(), ownerId);
			throw new ForbiddenException(NO_PERMISSION_TO_UPDATE_DISH);
		}
		Dish updated = new Dish(
				existing.id(),
				existing.name(),
				price,
				description,
				existing.urlImage(),
				existing.category(),
				existing.active(),
				existing.restaurantId());
		Dish saved = dishRepositoryPort.save(updated);
		log.info("dish.update.completed id={} restaurantId={}", saved.id(), saved.restaurantId());
		return saved;
	}
}
