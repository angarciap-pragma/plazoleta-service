package com.plazoleta.service.infrastructure.adapter.out.persistence.mapper;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantEntityMapper {

	public RestaurantEntity toEntity(Restaurant restaurant) {
		RestaurantEntity entity = new RestaurantEntity();
		entity.setId(restaurant.id());
		entity.setName(restaurant.name());
		entity.setNit(restaurant.nit());
		entity.setAddress(restaurant.address());
		entity.setPhone(restaurant.phone());
		entity.setUrlLogo(restaurant.urlLogo());
		entity.setOwnerId(restaurant.ownerId());
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
