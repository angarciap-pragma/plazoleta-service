package com.plazoleta.service.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {

	private Long id;
	private String name;
	private Integer price;
	private String description;
	private String urlImage;
	private String category;
	private boolean active;
	private Long restaurantId;

}
