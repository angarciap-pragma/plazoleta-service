package com.plazoleta.service.infrastructure.adapter.in.web.dto;

public class DishResponse {

	private Long id;
	private String name;
	private Integer price;
	private String description;
	private String urlImage;
	private String category;
	private boolean active;
	private Long restaurantId;

	public DishResponse() {
	}

	public DishResponse(Long id, String name, Integer price, String description, String urlImage, String category, boolean active, Long restaurantId) {
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
}
