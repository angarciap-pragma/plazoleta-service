package com.plazoleta.service.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantCreateRequest {

	@NotBlank(message = "El nombre es obligatorio.")
	//@ContainsLetter(message = "El nombre del restaurante debe contener al menos una letra")
	@Pattern(regexp = ".*[a-zA-Z].*",
			message = "El nombre del restaurante debe contener al menos una letra")
	private String name;

	@NotBlank(message = "El NIT es obligatorio.")
	@Pattern(regexp = "^\\d+$", message = "El NIT debe ser numerico.")
	private String nit;

	@NotBlank(message = "La direccion es obligatoria.")
	private String address;

	@NotBlank(message = "El telefono es obligatorio.")
	@Size(max = 13, message = "El telefono debe tener maximo 13 caracteres.")
	@Pattern(regexp = "^\\+?\\d+$", message = "El telefono debe ser numerico y puede iniciar con +.")
	private String phone;

	@NotBlank(message = "La url del logo es obligatoria.")
	private String urlLogo;

	@NotNull(message = "El id del propietario es obligatorio.")
	private Long ownerId;

}
