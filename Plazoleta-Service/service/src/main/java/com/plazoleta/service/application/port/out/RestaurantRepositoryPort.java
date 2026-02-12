package com.plazoleta.service.application.port.out;

import com.plazoleta.service.domain.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepositoryPort {

	Restaurant save(Restaurant restaurant);

	Optional<Restaurant> findById(Long id);
}
