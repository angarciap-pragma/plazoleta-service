package com.plazoleta.service.domain.model;

public record Dish(Long id, String name, Integer price, String description, String urlImage, String category,
				   boolean active, Long restaurantId) {

}
