package com.plazoleta.service.infrastructure.adapter.in.web.mapper;

import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.request.DishCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.DishResponse;
import org.springframework.stereotype.Component;

@Component
public class DishWebMapper {

	public Dish toDomain(DishCreateRequest request) {
		return new Dish(
				null,
				request.getName(),
				request.getPrice(),
				request.getDescription(),
				request.getUrlImage(),
				request.getCategory(),
				true,
				request.getRestaurantId());
	}

	public DishResponse toResponse(Dish dish) {
		return new DishResponse(
				dish.id(),
				dish.name(),
				dish.price(),
				dish.description(),
				dish.urlImage(),
				dish.category(),
				dish.active(),
				dish.restaurantId());
	}
}
