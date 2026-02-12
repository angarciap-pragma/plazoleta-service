package com.plazoleta.service.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
