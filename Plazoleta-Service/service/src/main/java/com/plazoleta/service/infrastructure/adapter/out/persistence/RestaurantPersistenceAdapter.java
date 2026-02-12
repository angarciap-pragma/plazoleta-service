package com.plazoleta.service.infrastructure.adapter.out.persistence;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.application.port.out.RestaurantRepositoryPort;
import com.plazoleta.service.infrastructure.adapter.out.persistence.mapper.RestaurantEntityMapper;
import com.plazoleta.service.infrastructure.adapter.out.persistence.repository.RestaurantJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RestaurantPersistenceAdapter implements RestaurantRepositoryPort {

	private final RestaurantJpaRepository restaurantJpaRepository;
	private final RestaurantEntityMapper restaurantEntityMapper = new RestaurantEntityMapper();

	public RestaurantPersistenceAdapter(RestaurantJpaRepository restaurantJpaRepository) {
		this.restaurantJpaRepository = restaurantJpaRepository;
	}

	@Override
	public Restaurant save(Restaurant restaurant) {
		var entity = restaurantEntityMapper.toEntity(restaurant);
		var saved = restaurantJpaRepository.save(entity);
		log.debug("restaurant.persisted id={} nit={}", saved.getId(), saved.getNit());
		return restaurantEntityMapper.toDomain(saved);
	}

	@Override
	public java.util.Optional<Restaurant> findById(Long id) {
		return restaurantJpaRepository.findById(id)
				.map(restaurantEntityMapper::toDomain);
	}
}
