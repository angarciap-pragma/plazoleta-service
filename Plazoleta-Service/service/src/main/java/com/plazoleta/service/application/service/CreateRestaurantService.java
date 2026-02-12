package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.ForbiddenException;
import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.application.port.in.CreateRestaurantUseCase;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.application.port.out.UserServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateRestaurantService implements CreateRestaurantUseCase {

	private static final String OWNER_ROLE = "PROPIETARIO";
	private static final String USER_IS_NOT_OWNER = "El usuario no tiene el rol de propietario.";

	private final RestaurantRepositoryPort restaurantRepositoryPort;
	private final UserServicePort userServicePort;

	@Override
	public Restaurant create(Restaurant restaurant) {
		log.info("restaurant.create.request ownerId={}", restaurant.ownerId());
		String role = userServicePort.getRoleByUserId(restaurant.ownerId());
		if (role == null || !OWNER_ROLE.equalsIgnoreCase(role.trim())) {
			log.warn("Owner role validation failed userId={} role={}", restaurant.ownerId(), role);
			throw new ForbiddenException(USER_IS_NOT_OWNER);
		}
		Restaurant saved = restaurantRepositoryPort.save(restaurant);
		log.info("restaurant.create.completed idRestaurant={} ownerId={}", saved.id(), saved.ownerId());
		return saved;
	}
}
