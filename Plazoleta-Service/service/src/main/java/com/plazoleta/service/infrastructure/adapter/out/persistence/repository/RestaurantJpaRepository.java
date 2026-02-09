package com.plazoleta.service.infrastructure.adapter.out.persistence.repository;

import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {
}
