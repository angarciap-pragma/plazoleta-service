package com.plazoleta.service.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

	private Long id;
	private String name;
	private String nit;
	private String address;
	private String phone;
	private String urlLogo;
	private Long ownerId;

}
