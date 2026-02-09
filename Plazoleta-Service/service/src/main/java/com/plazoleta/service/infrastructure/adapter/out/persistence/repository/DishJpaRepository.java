package com.plazoleta.service.infrastructure.adapter.out.persistence.repository;

import com.plazoleta.service.infrastructure.adapter.out.persistence.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishJpaRepository extends JpaRepository<DishEntity, Long> {
}
