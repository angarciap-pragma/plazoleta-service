package com.plazoleta.service.infrastructure.adapter.out.persistence.mapper;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.RestaurantEntity;

public class RestaurantEntityMapper {

	public RestaurantEntity toEntity(Restaurant restaurant) {
		RestaurantEntity entity = new RestaurantEntity();
		entity.setId(restaurant.getId());
		entity.setName(restaurant.getName());
		entity.setNit(restaurant.getNit());
		entity.setAddress(restaurant.getAddress());
		entity.setPhone(restaurant.getPhone());
		entity.setUrlLogo(restaurant.getUrlLogo());
		entity.setOwnerId(restaurant.getOwnerId());
		return entity;
	}

	public Restaurant toDomain(RestaurantEntity entity) {
		return new Restaurant(
				entity.getId(),
				entity.getName(),
				entity.getNit(),
				entity.getAddress(),
				entity.getPhone(),
				entity.getUrlLogo(),
				entity.getOwnerId());
	}
}
