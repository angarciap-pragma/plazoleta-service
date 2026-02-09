package com.plazoleta.service.domain.model;

public class Restaurant {

	private final Long id;
	private final String name;
	private final String nit;
	private final String address;
	private final String phone;
	private final String urlLogo;
	private final Long ownerId;

	public Restaurant(Long id, String name, String nit, String address, String phone, String urlLogo, Long ownerId) {
		this.id = id;
		this.name = name;
		this.nit = nit;
		this.address = address;
		this.phone = phone;
		this.urlLogo = urlLogo;
		this.ownerId = ownerId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNit() {
		return nit;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public Long getOwnerId() {
		return ownerId;
	}
}
