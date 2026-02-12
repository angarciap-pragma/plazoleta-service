package com.plazoleta.service.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.plazoleta.service.domain.exception.UnauthorizedException;
import com.plazoleta.service.infrastructure.security.adapter.SecurityCurrentUserAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

class SecurityCurrentUserAdapterTest {

	private final SecurityCurrentUserAdapter adapter = new SecurityCurrentUserAdapter();

	@AfterEach
	void clearContext() {
		SecurityContextHolder.clearContext();
	}

	@Test
	void returnUserIdWhenAuthenticated() {
		// Contexto autenticado con userId en el principal.
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken("15", null));

		// Llamada al adaptador.
		Long userId = adapter.getCurrentUserId();

		// Asercion unica: el userId debe coincidir.
		assertThat(userId).isEqualTo(15L);
	}

	@Test
	void throwWhenUnauthenticated() {
		// Contexto sin autenticacion.
		SecurityContextHolder.clearContext();

		// Asercion unica: debe lanzar excepcion.
		assertThatThrownBy(() -> adapter.getCurrentUserId())
				.isInstanceOf(UnauthorizedException.class);
	}
}



