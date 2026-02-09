package com.plazoleta.service.application.service;

import com.plazoleta.service.domain.exception.InvalidOwnerRoleException;
import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.domain.port.in.CreateRestaurantUseCase;
import com.plazoleta.service.domain.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.domain.port.out.UserServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRestaurantService implements CreateRestaurantUseCase {

	private static final String OWNER_ROLE = "PROPIETARIO";

	private final RestaurantRepositoryPort restaurantRepositoryPort;
	private final UserServicePort userServicePort;

	public CreateRestaurantService(
			RestaurantRepositoryPort restaurantRepositoryPort,
			UserServicePort userServicePort) {
		this.restaurantRepositoryPort = restaurantRepositoryPort;
		this.userServicePort = userServicePort;
	}

	@Override
	public Restaurant create(Restaurant restaurant) {
		String role = userServicePort.getRoleByUserId(restaurant.getOwnerId());
		if (role == null || !OWNER_ROLE.equalsIgnoreCase(role.trim())) {
			log.warn("Owner role validation failed userId={} role={}", restaurant.getOwnerId(), role);
			throw new InvalidOwnerRoleException("El usuario no tiene el rol de propietario.");
		}
		return restaurantRepositoryPort.save(restaurant);
	}
}
