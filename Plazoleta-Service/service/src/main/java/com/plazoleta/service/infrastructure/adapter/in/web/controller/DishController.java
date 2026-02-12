package com.plazoleta.service.infrastructure.adapter.in.web.controller;

import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.application.port.in.CreateDishUseCase;
import com.plazoleta.service.application.port.in.UpdateDishUseCase;
import com.plazoleta.service.application.port.out.CurrentUserPort;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.request.DishCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.DishResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.request.DishUpdateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.response.ErrorResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.mapper.DishWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/platos")
@RequiredArgsConstructor
public class DishController {

	private final CreateDishUseCase createDishUseCase;
	private final UpdateDishUseCase updateDishUseCase;
	private final DishWebMapper dishWebMapper;
	private final CurrentUserPort currentUserPort;

	@Operation(summary = "Crear plato")
			@ApiResponse(responseCode = "201", description = "Plato creado")
			@ApiResponse(responseCode = "400", description = "Solicitud invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
			@ApiResponse(responseCode = "401", description = "No autorizado")
			@ApiResponse(responseCode = "403", description = "No permitido")
			@ApiResponse(responseCode = "404", description = "Restaurante no encontrado")
	@PostMapping
	public ResponseEntity<DishResponse> create(@Valid @RequestBody DishCreateRequest request) {
		log.info("--- Start create dish ---");
		Long ownerId = currentUserPort.getCurrentUserId();
		Dish created = createDishUseCase.create(dishWebMapper.toDomain(request), ownerId);
		log.info("--- End create dish ---");
		return ResponseEntity.status(HttpStatus.CREATED).body(dishWebMapper.toResponse(created));
	}

	@Operation(summary = "Modificar plato")
			@ApiResponse(responseCode = "200", description = "Plato actualizado")
			@ApiResponse(responseCode = "400", description = "Solicitud invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
			@ApiResponse(responseCode = "401", description = "No autorizado")
			@ApiResponse(responseCode = "403", description = "No permitido")
			@ApiResponse(responseCode = "404", description = "Plato no encontrado")
	@PatchMapping("/{id}")
	public ResponseEntity<DishResponse> update(@PathVariable("id") Long dishId, @Valid @RequestBody DishUpdateRequest request) {
		log.info("--- Start update dish ---");
		Long ownerId = currentUserPort.getCurrentUserId();
		Dish updated = updateDishUseCase.update(dishId, request.getPrice(), request.getDescription(), ownerId);
		log.info("--- End update dish ---");
		return ResponseEntity.ok(dishWebMapper.toResponse(updated));
	}

}
