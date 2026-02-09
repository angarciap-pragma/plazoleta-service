package com.plazoleta.service.infrastructure.adapter.out.persistence.mapper;

import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.DishEntity;

public class DishEntityMapper {

	public DishEntity toEntity(Dish dish) {
		DishEntity entity = new DishEntity();
		entity.setId(dish.getId());
		entity.setName(dish.getName());
		entity.setPrice(dish.getPrice());
		entity.setDescription(dish.getDescription());
		entity.setUrlImage(dish.getUrlImage());
		entity.setCategory(dish.getCategory());
		entity.setActive(dish.isActive());
		entity.setRestaurantId(dish.getRestaurantId());
		return entity;
	}

	public Dish toDomain(DishEntity entity) {
		return new Dish(
				entity.getId(),
				entity.getName(),
				entity.getPrice(),
				entity.getDescription(),
				entity.getUrlImage(),
				entity.getCategory(),
				entity.isActive(),
				entity.getRestaurantId());
	}
}
