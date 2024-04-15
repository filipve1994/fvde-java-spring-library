package io.filipvde.customspringbootstarter.securitystarter.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenManager.class);


	private final JwtProperties jwtProperties;

	public JwtTokenManager(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}


	// Generate JWT token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();

		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtProperties.getExpirationMilliseconds());

		String token = Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(expireDate)
			.signWith(key())
			.compact();

		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
	}

	// Get username from JWT token
	public String getUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
		Claims claims = Jwts.parser()
			.setSigningKey(key())
			.build()
			.parseClaimsJws(token)
			.getBody();

		String username = claims.getSubject();

		return username;
	}

	// Validate JWT Token
	public boolean validateToken(String token) {
//        Jwts.parserBuilder()
		Jwts.parser()
			.setSigningKey(key())
			.build()
			.parse(token);
		return true;
	}
}
