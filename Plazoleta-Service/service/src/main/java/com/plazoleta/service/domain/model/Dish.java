package com.plazoleta.service.domain.model;

public class Dish {

	private final Long id;
	private final String name;
	private final Integer price;
	private final String description;
	private final String urlImage;
	private final String category;
	private final boolean active;
	private final Long restaurantId;

	public Dish(Long id, String name, Integer price, String description, String urlImage, String category, boolean active, Long restaurantId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.urlImage = urlImage;
		this.category = category;
		this.active = active;
		this.restaurantId = restaurantId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public String getCategory() {
		return category;
	}

	public boolean isActive() {
		return active;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}
}
