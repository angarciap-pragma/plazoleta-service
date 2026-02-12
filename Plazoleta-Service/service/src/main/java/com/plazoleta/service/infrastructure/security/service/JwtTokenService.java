package com.plazoleta.service.infrastructure.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtTokenService {

	// Encapsula la validacion y lectura de claims del JWT.
	private final SecretKey key;

	public JwtTokenService(@Value("${jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String getSubject(String token) {
		// Subject tipico del JWT (usuario principal).
		return parseClaims(token).getSubject();
	}

	public String getUserId(String token) {
		// Busca el id del usuario en distintos claims usados por servicios externos.
		Claims claims = parseClaims(token);
		Object candidate = claims.get("userId");
		if (candidate == null) {
			candidate = claims.get("id");
		}
		if (candidate == null) {
			candidate = claims.getSubject();
		}
		if (candidate == null) {
			return null;
		}
		String value = candidate.toString().trim();
		return value.isEmpty() ? null : value;
	}

	public List<SimpleGrantedAuthority> getAuthorities(String token) {
		// Convierte los claims de roles/authorities en GrantedAuthority.
		Claims claims = parseClaims(token);
		Object raw = claims.get("authorities");
		if (raw == null) {
			raw = claims.get("roles");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (raw instanceof Collection<?> collection) {
			for (Object value : collection) {
				addAuthority(authorities, value);
			}
		} else if (raw instanceof String value) {
			for (String tokenValue : value.split(",")) {
				addAuthority(authorities, tokenValue);
			}
		}
		return authorities;
	}

	private void addAuthority(List<SimpleGrantedAuthority> authorities, Object value) {
		if (value == null) {
			return;
		}
		String text = value.toString().trim();
		if (!text.isEmpty()) {
			authorities.add(new SimpleGrantedAuthority(text));
		}
	}

	private Claims parseClaims(String token) {
		// Valida firma y expiracion; si falla, lanza excepciones del parser.
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
