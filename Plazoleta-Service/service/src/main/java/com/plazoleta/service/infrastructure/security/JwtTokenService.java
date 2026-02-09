package com.plazoleta.service.infrastructure.security;

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

	private final SecretKey key;

	public JwtTokenService(@Value("${jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	public List<SimpleGrantedAuthority> getAuthorities(String token) {
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
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
