package com.plazoleta.service.infrastructure.config;

import com.plazoleta.service.application.service.CreateDishService;
import com.plazoleta.service.application.service.CreateRestaurantService;
import com.plazoleta.service.application.service.UpdateDishService;
import com.plazoleta.service.application.port.in.CreateDishUseCase;
import com.plazoleta.service.application.port.in.CreateRestaurantUseCase;
import com.plazoleta.service.application.port.in.UpdateDishUseCase;
import com.plazoleta.service.application.port.out.DishRepositoryPort;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.application.port.out.UserServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

	@Bean
	public CreateRestaurantUseCase createRestaurantUseCase(
			RestaurantRepositoryPort restaurantRepositoryPort,
			UserServicePort userServicePort) {
		return new CreateRestaurantService(restaurantRepositoryPort, userServicePort);
	}

	@Bean
	public CreateDishUseCase createDishUseCase(
			DishRepositoryPort dishRepositoryPort,
			RestaurantRepositoryPort restaurantRepositoryPort) {
		return new CreateDishService(dishRepositoryPort, restaurantRepositoryPort);
	}

	@Bean
	public UpdateDishUseCase updateDishUseCase(
			DishRepositoryPort dishRepositoryPort,
			RestaurantRepositoryPort restaurantRepositoryPort) {
		return new UpdateDishService(dishRepositoryPort, restaurantRepositoryPort);
	}
}
