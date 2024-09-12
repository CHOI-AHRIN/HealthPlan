package com.healthplan.work.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
	private static final String SECRET_KEY = "240930_healthplan_backend"; // Replace with a secure secret key
	private static final long EXPIRATION_TIME = 600000; // 1 hours

	public static String generateToken(String uuid) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", uuid);
		claims.put("created", new Date());

		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public static String getUuidFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	

	public static boolean validateToken(String token, String uuid) {
		String tokenUsername = getUuidFromToken(token);
		return (tokenUsername.equals(uuid) && !isTokenExpired(token));
	}


	private static boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}


}
