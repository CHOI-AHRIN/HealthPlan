package com.healthplan.work.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	// Base64.겟인코더.인코드투스트링과, getBytes로 변경
	// private static final String SECRET_KEY = Base64.getEncoder().encodeToString("240930_healthplan_backend".getBytes()); // Replace with a secure secret key
	private static final long EXPIRATION_TIME = 600000; // 1 hours

	public static String generateToken(String uuid) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", uuid);
		claims.put("created", new Date());

		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	
	// 240923 > Jwts.parser() 대신 parserBuilder()를 사용하여 토큰을 파싱하는 방법으로 수정
	public static String getUuidFromToken(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject(); // claims에서 subject (uuid) 추출
	}
	

	public static boolean validateToken(String token, String uuid) {
		String tokenUsername = getUuidFromToken(token);
		if (tokenUsername == null) {
			// 토큰에서 유효한 UUID를 추출하지 못한 경우
			return false;
		}
		return (tokenUsername.equals(uuid) && !isTokenExpired(token));
	}


	private static boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}


}
