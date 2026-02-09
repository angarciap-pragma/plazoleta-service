package com.plazoleta.service.domain.port.in;

import com.plazoleta.service.domain.model.Restaurant;

public interface CreateRestaurantUseCase {

	Restaurant create(Restaurant restaurant);
}
