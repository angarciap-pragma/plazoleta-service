package com.plazoleta.service.domain.port.in;

import com.plazoleta.service.domain.model.Dish;

public interface CreateDishUseCase {

	Dish create(Dish dish, Long ownerId);
}
