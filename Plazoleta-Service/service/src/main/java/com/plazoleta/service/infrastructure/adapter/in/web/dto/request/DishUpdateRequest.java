package com.plazoleta.service.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DishUpdateRequest {

	@NotNull(message = "El precio es obligatorio.")
	@Min(value = 1, message = "El precio debe ser mayor a 0.")
	private Integer price;

	@NotBlank(message = "La descripcion es obligatoria.")
	private String description;

}
