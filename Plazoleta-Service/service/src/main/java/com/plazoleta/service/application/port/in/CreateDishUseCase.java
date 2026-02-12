package com.plazoleta.service.application.port.in;

import com.plazoleta.service.domain.model.Dish;

public interface CreateDishUseCase {

	Dish create(Dish dish, Long ownerId);
}
