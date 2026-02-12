package com.plazoleta.service.infrastructure.security.adapter;

import com.plazoleta.service.domain.exception.UnauthorizedException;
import com.plazoleta.service.application.port.out.CurrentUserPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityCurrentUserAdapter implements CurrentUserPort {

	private static final String USER_NOT_AUTHENTICATED
			= "No autenticado.";
	private static final String USER_ID_INVALID = "Identificador de usuario invalido.";

	@Override
	public Long getCurrentUserId() {
		// Lee el usuario autenticado desde el SecurityContext; si no existe, no hay sesion valida.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null) {
			throw new UnauthorizedException(USER_NOT_AUTHENTICATED
			);
		}
		try {
			return Long.parseLong(authentication.getName());
		} catch (NumberFormatException _) {
			throw new UnauthorizedException(USER_ID_INVALID);
		}
	}
}
