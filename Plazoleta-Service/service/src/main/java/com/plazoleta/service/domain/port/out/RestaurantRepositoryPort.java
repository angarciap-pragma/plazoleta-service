package com.plazoleta.service.domain.port.out;

import com.plazoleta.service.domain.model.Restaurant;

public interface RestaurantRepositoryPort {

	Restaurant save(Restaurant restaurant);
}
