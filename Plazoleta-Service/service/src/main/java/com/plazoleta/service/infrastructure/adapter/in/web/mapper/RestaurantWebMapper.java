package com.plazoleta.service.infrastructure.adapter.in.web.mapper;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.request.RestaurantCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.RestaurantResponse;
import org.springframework.stereotype.Component;

@Component
public class RestaurantWebMapper {

	public Restaurant toDomain(RestaurantCreateRequest request) {
		return new Restaurant(
				null,
				request.getName(),
				request.getNit(),
				request.getAddress(),
				request.getPhone(),
				request.getUrlLogo(),
				request.getOwnerId());
	}

	public RestaurantResponse toResponse(Restaurant restaurant) {
		return new RestaurantResponse(
				restaurant.id(),
				restaurant.name(),
				restaurant.nit(),
				restaurant.address(),
				restaurant.phone(),
				restaurant.urlLogo(),
				restaurant.ownerId());
	}
}
