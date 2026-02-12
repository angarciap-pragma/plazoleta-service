package com.plazoleta.service.infrastructure.adapter.out.persistence.mapper;

import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.DishEntity;
import org.springframework.stereotype.Component;

@Component
public class DishEntityMapper {

	public DishEntity toEntity(Dish dish) {
		DishEntity entity = new DishEntity();
		entity.setId(dish.id());
		entity.setName(dish.name());
		entity.setPrice(dish.price());
		entity.setDescription(dish.description());
		entity.setUrlImage(dish.urlImage());
		entity.setCategory(dish.category());
		entity.setActive(dish.active());
		entity.setRestaurantId(dish.restaurantId());
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
