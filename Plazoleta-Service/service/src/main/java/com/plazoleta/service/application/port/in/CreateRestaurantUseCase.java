package com.plazoleta.service.application.port.in;

import com.plazoleta.service.domain.model.Restaurant;

public interface CreateRestaurantUseCase {

	Restaurant create(Restaurant restaurant);
}
