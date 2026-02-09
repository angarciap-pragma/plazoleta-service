package com.plazoleta.service.infrastructure.adapter.out.persistence;

import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.port.out.DishRepositoryPort;
import com.plazoleta.service.infrastructure.adapter.out.persistence.mapper.DishEntityMapper;
import com.plazoleta.service.infrastructure.adapter.out.persistence.repository.DishJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DishPersistenceAdapter implements DishRepositoryPort {

	private final DishJpaRepository dishJpaRepository;
	private final DishEntityMapper dishEntityMapper = new DishEntityMapper();

	public DishPersistenceAdapter(DishJpaRepository dishJpaRepository) {
		this.dishJpaRepository = dishJpaRepository;
	}

	@Override
	public Dish save(Dish dish) {
		var entity = dishEntityMapper.toEntity(dish);
		var saved = dishJpaRepository.save(entity);
		log.info("Dish persisted id={} restaurantId={}", saved.getId(), saved.getRestaurantId());
		return dishEntityMapper.toDomain(saved);
	}
}
