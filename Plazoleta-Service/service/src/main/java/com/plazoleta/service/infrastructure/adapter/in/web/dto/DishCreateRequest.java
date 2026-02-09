package com.plazoleta.service.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DishCreateRequest {

	@NotBlank(message = "El nombre del plato es obligatorio.")
	private String name;

	@NotNull(message = "El precio es obligatorio.")
	@Min(value = 1, message = "El precio debe ser mayor a 0.")
	private Integer price;

	@NotBlank(message = "La descripcion es obligatoria.")
	private String description;

	@NotBlank(message = "La url de la imagen es obligatoria.")
	private String urlImage;

	@NotBlank(message = "La categoria es obligatoria.")
	private String category;

	@NotNull(message = "El restaurante es obligatorio.")
	private Long restaurantId;

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

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
}
