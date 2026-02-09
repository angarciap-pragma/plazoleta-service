package com.plazoleta.service.infrastructure.adapter.in.web.dto;

import com.plazoleta.service.infrastructure.adapter.in.web.validation.ContainsLetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RestaurantCreateRequest {

	@NotBlank(message = "El nombre es obligatorio.")
	@ContainsLetter
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
