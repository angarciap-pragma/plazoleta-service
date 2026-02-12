package com.plazoleta.service.infrastructure.adapter.in.web.controller;

import com.plazoleta.service.domain.model.Restaurant;
import com.plazoleta.service.application.port.in.CreateRestaurantUseCase;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.ErrorResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.request.RestaurantCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.RestaurantResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.mapper.RestaurantWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestaurantController {

	private final CreateRestaurantUseCase createRestaurantUseCase;
	private final RestaurantWebMapper restaurantWebMapper;

	@Operation(summary = "Crear restaurante")
	@ApiResponse(responseCode = "201", description = "Restaurante creado")
	@ApiResponse(responseCode = "400", description = "Solicitud invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "401", description = "No autorizado")
	@PostMapping
	public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody RestaurantCreateRequest request) {
		log.info("--- Start create restaurant ---");
		Restaurant created = createRestaurantUseCase.create(restaurantWebMapper.toDomain(request));
		log.info("--- End create restaurant ---");
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantWebMapper.toResponse(created));
	}
}
