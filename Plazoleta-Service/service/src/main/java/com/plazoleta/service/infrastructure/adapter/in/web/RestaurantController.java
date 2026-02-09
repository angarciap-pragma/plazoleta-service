package com.plazoleta.service.infrastructure.adapter.in.web;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.domain.port.in.CreateRestaurantUseCase;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.ErrorResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.RestaurantCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.RestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
@Slf4j
public class RestaurantController {

	private final CreateRestaurantUseCase createRestaurantUseCase;

	public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase) {
		this.createRestaurantUseCase = createRestaurantUseCase;
	}

	@Operation(summary = "Crear restaurante")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Restaurante creado"),
			@ApiResponse(responseCode = "400", description = "Solicitud invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "No autorizado")
	})
	@PostMapping
	public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody RestaurantCreateRequest request) {
		Restaurant created = createRestaurantUseCase.create(toDomain(request));
		log.info("Restaurant created id={} ownerId={}", created.getId(), created.getOwnerId());
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	private Restaurant toDomain(RestaurantCreateRequest request) {
		return new Restaurant(
				null,
				request.getName(),
				request.getNit(),
				request.getAddress(),
				request.getPhone(),
				request.getUrlLogo(),
				request.getOwnerId());
	}

	private RestaurantResponse toResponse(Restaurant restaurant) {
		return new RestaurantResponse(
				restaurant.getId(),
				restaurant.getName(),
				restaurant.getNit(),
				restaurant.getAddress(),
				restaurant.getPhone(),
				restaurant.getUrlLogo(),
				restaurant.getOwnerId());
	}
}
