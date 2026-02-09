package com.plazoleta.service.domain.port.out;

import com.plazoleta.service.domain.model.Dish;

public interface DishRepositoryPort {

	Dish save(Dish dish);

	java.util.Optional<Dish> findById(Long id);
}
