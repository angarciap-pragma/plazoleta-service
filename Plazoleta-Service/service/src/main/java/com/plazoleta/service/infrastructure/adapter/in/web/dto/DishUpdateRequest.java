package com.plazoleta.service.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DishUpdateRequest {

	@NotNull(message = "El precio es obligatorio.")
	@Min(value = 1, message = "El precio debe ser mayor a 0.")
	private Integer price;

	@NotBlank(message = "La descripcion es obligatoria.")
	private String description;

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
}
