package com.plazoleta.service.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.plazoleta.service.infrastructure.security.service.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class JwtTokenServiceTest {

	private static final String SECRET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef";

	@Test
	void readSubject() {
		// Token valido con subject, userId y authorities.
		String token = Jwts.builder()
				.subject("subject-1")
				.claim("userId", "42")
				.claim("authorities", List.of("PROPIETARIO", "ADMIN"))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
				.compact();

		// Servicio real a probar.
		JwtTokenService service = new JwtTokenService(SECRET);

		// Asercion unica: el subject debe coincidir.
		assertThat(service.getSubject(token)).isEqualTo("subject-1");
	}

	@Test
	void readUserId() {
		// Token valido con subject, userId y authorities.
		String token = Jwts.builder()
				.subject("subject-1")
				.claim("userId", "42")
				.claim("authorities", List.of("PROPIETARIO", "ADMIN"))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
				.compact();

		// Servicio real a probar.
		JwtTokenService service = new JwtTokenService(SECRET);

		// Asercion unica: el userId debe coincidir.
		assertThat(service.getUserId(token)).isEqualTo("42");
	}

	@Test
	void readAuthorities() {
		// Token valido con subject, userId y authorities.
		String token = Jwts.builder()
				.subject("subject-1")
				.claim("userId", "42")
				.claim("authorities", List.of("PROPIETARIO", "ADMIN"))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
				.compact();

		// Servicio real a probar.
		JwtTokenService service = new JwtTokenService(SECRET);

		// Asercion unica: las authorities deben coincidir.
		List<SimpleGrantedAuthority> authorities = service.getAuthorities(token);
		assertThat(authorities)
				.extracting(SimpleGrantedAuthority::getAuthority)
				.containsExactly("PROPIETARIO", "ADMIN");
	}
}


