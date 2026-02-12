package com.plazoleta.service.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "dishes")
public class DishEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false, length = 1024)
	private String description;

	@Column(nullable = false)
	private String urlImage;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private boolean active;

	@Column(nullable = false)
	private Long restaurantId;

}
