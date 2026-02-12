package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.NotFoundException;
import com.plazoleta.service.domain.exception.ForbiddenException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.application.port.in.CreateDishUseCase;
import com.plazoleta.service.application.port.out.DishRepositoryPort;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateDishService implements CreateDishUseCase {

	private static final String RESTAURANT_NOT_FOUND = "Restaurante no encontrado.";
	private static final String NO_PERMISSION_TO_CREATE_DISH = "No tiene permisos para crear platos en este restaurante.";

	private final DishRepositoryPort dishRepositoryPort;
	private final RestaurantRepositoryPort restaurantRepositoryPort;

	@Override
	public Dish create(Dish dish, Long ownerId) {
		log.info("dish.create.request restaurantId={} requesterId={}", dish.restaurantId(), ownerId);
		var restaurant = restaurantRepositoryPort.findById(dish.restaurantId())
				.orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND));
		if (!restaurant.ownerId().equals(ownerId)) {
			log.warn("Owner mismatch restaurantId={} ownerId={} requester={}", restaurant.id(), restaurant.ownerId(), ownerId);
			throw new ForbiddenException(NO_PERMISSION_TO_CREATE_DISH);
		}
		Dish toSave = new Dish(
				null,
				dish.name(),
				dish.price(),
				dish.description(),
				dish.urlImage(),
				dish.category(),
				true,
				dish.restaurantId());
		Dish saved = dishRepositoryPort.save(toSave);
		log.info("dish.create.completed id={} restaurantId={}", saved.id(), saved.restaurantId());
		return saved;
	}
}
