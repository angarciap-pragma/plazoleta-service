package com.plazoleta.service.infrastructure.security.filter;

import com.plazoleta.service.infrastructure.security.service.JwtTokenService;
import com.plazoleta.service.infrastructure.security.RestAuthenticationEntryPoint;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// Extrae y valida el JWT para poblar el SecurityContext si el token es valido.
	private final JwtTokenService jwtTokenService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private static final String BEARER_PREFIX = "Bearer ";
	private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();
	private static final String EXPIRED_TOKEN = "Token expirado";
	private static final String INVALID_TOKEN = "Token invalido";

	@Override
	protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
		// Solo procesa si viene un Bearer token; si no, deja continuar para endpoints publicos.
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith(BEARER_PREFIX)) {
			String token = header.substring(BEARER_PREFIX_LENGTH);
			try {
				String subject = jwtTokenService.getSubject(token);
				String userId = jwtTokenService.getUserId(token);
				var authorities = jwtTokenService.getAuthorities(token);
				var authentication = new UsernamePasswordAuthenticationToken(
						userId != null ? userId : subject,
						null,
						authorities);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.debug("jwt.authenticated subject={} authorities={}", subject, authorities);
			} catch (Exception ex) {
				String message = ex instanceof ExpiredJwtException ? EXPIRED_TOKEN : INVALID_TOKEN;
				log.warn("jwt.authentication.failed reason={}", ex.getClass().getSimpleName());
				SecurityContextHolder.clearContext();
				restAuthenticationEntryPoint.commence(
						request,
						response,
						new BadCredentialsException(message, ex));
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}
