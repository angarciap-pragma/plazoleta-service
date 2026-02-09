package com.plazoleta.service.domain.port.in;

import com.plazoleta.service.domain.model.Dish;

public interface UpdateDishUseCase {

	Dish update(Long dishId, Integer price, String description, Long ownerId);
}
