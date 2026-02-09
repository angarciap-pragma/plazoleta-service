package com.plazoleta.service.infrastructure.adapter.in.web;

import com.plazoleta.service.domain.exception.UnauthorizedException;
import com.plazoleta.service.domain.model.Dish;
import com.plazoleta.service.domain.port.in.CreateDishUseCase;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.DishCreateRequest;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.DishResponse;
import com.plazoleta.service.infrastructure.adapter.in.web.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platos")
@Slf4j
public class DishController {

	private final CreateDishUseCase createDishUseCase;

	public DishController(CreateDishUseCase createDishUseCase) {
		this.createDishUseCase = createDishUseCase;
	}

	@Operation(summary = "Crear plato")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Plato creado"),
			@ApiResponse(responseCode = "400", description = "Solicitud invalida", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "No autorizado"),
			@ApiResponse(responseCode = "403", description = "No permitido"),
			@ApiResponse(responseCode = "404", description = "Restaurante no encontrado")
	})
	@PostMapping
	public ResponseEntity<DishResponse> create(@Valid @RequestBody DishCreateRequest request) {
		Long ownerId = resolveOwnerId();
		Dish created = createDishUseCase.create(toDomain(request), ownerId);
		log.info("Dish created id={} restaurantId={}", created.getId(), created.getRestaurantId());
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	private Long resolveOwnerId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null) {
			throw new UnauthorizedException("No autenticado.");
		}
		try {
			return Long.parseLong(authentication.getName());
		} catch (NumberFormatException ex) {
			throw new UnauthorizedException("Identificador de usuario invalido.");
		}
	}

	private Dish toDomain(DishCreateRequest request) {
		return new Dish(
				null,
				request.getName(),
				request.getPrice(),
				request.getDescription(),
				request.getUrlImage(),
				request.getCategory(),
				true,
				request.getRestaurantId());
	}

	private DishResponse toResponse(Dish dish) {
		return new DishResponse(
				dish.getId(),
				dish.getName(),
				dish.getPrice(),
				dish.getDescription(),
				dish.getUrlImage(),
				dish.getCategory(),
				dish.isActive(),
				dish.getRestaurantId());
	}
}
