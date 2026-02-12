package com.plazoleta.service.infrastructure.security;

import com.plazoleta.service.infrastructure.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String SWAGGER_DOCS = "/v3/api-docs/**";
	private static final String SWAGGER_UI = "/swagger-ui/**";
	private static final String SWAGGER_UI_HTML = "/swagger-ui.html";
	private static final String RESTAURANTES = "/restaurantes";
	private static final String PLATOS = "/platos";
	private static final String PLATOS_ALL = "/platos/**";
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_ADMINISTRADOR = "ADMINISTRADOR";
	private static final String ROLE_PROPIETARIO = "PROPIETARIO";

	// Centraliza reglas de seguridad HTTP: autenticacion, autorizacion y manejo de errores.
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final RestAccessDeniedHandler restAccessDeniedHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Configura el flujo de seguridad en modo stateless con JWT.
		http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(restAuthenticationEntryPoint)
						.accessDeniedHandler(restAccessDeniedHandler))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(SWAGGER_DOCS, SWAGGER_UI, SWAGGER_UI_HTML).permitAll()
						.requestMatchers(HttpMethod.POST, RESTAURANTES)
						.hasAnyAuthority(ROLE_ADMIN, ROLE_ADMINISTRADOR)
						.requestMatchers(HttpMethod.POST, PLATOS)
						.hasAnyAuthority(ROLE_PROPIETARIO)
						.requestMatchers(HttpMethod.PATCH, PLATOS_ALL)
						.hasAnyAuthority(ROLE_PROPIETARIO)
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
