package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.DishNotFoundException;
import com.plazoleta.service.domain.exception.UnauthorizedOwnerException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.port.in.UpdateDishUseCase;
import com.plazoleta.service.domain.port.out.DishRepositoryPort;
import com.plazoleta.service.domain.port.out.RestaurantRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateDishService implements UpdateDishUseCase {

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
		Dish existing = dishRepositoryPort.findById(dishId)
				.orElseThrow(() -> new DishNotFoundException("Plato no encontrado."));
		var restaurant = restaurantRepositoryPort.findById(existing.getRestaurantId())
				.orElseThrow(() -> new DishNotFoundException("Restaurante no encontrado para el plato."));
		if (!restaurant.getOwnerId().equals(ownerId)) {
			log.warn("Owner mismatch for dishId={} restaurantId={} ownerId={} requester={}",
					dishId, restaurant.getId(), restaurant.getOwnerId(), ownerId);
			throw new UnauthorizedOwnerException("No tiene permisos para modificar este plato.");
		}
		Dish updated = new Dish(
				existing.getId(),
				existing.getName(),
				price,
				description,
				existing.getUrlImage(),
				existing.getCategory(),
				existing.isActive(),
				existing.getRestaurantId());
		return dishRepositoryPort.save(updated);
	}
}
