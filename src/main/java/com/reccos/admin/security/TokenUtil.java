package com.reccos.admin.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.reccos.admin.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

	private static final String EMISSOR = "Reccos";
	private static final String TOKEN_HEADER = "Bearer ";
	private static final String SECRET_KEY = "*N@tur@55pW3bS3cur1tyT0k3n202101";
	private static final long EXPIRATION = 120 * 60 * 1000;
//	private static final long EXPIRATION = 1000 * 60;
//	private static final String HEADER_AUTH = "Authorization";

	public static String encodeToken(User user) {

		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		String jwt = Jwts.builder().setSubject(user.getLogin()).setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
		return TOKEN_HEADER + jwt;
	}

	public static boolean isIssuerValid(String issuer) {
		return issuer.equals("Reccos");
	}

	public static boolean isSubjectValid(String subject) {
		return subject != null && subject.length() > 0;
	}

	public static boolean isExpirationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));
	}

	public static Authentication decodeToken(HttpServletRequest request) {
		try {

			String jwtToken = request.getHeader("Authorization");
			jwtToken = jwtToken.replace(TOKEN_HEADER, "");
			Jws<Claims> jswClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build()
					.parseClaimsJws(jwtToken);

			String usuario = jswClaims.getBody().getSubject();
			String emissor = jswClaims.getBody().getIssuer();
			Date validation = jswClaims.getBody().getExpiration();

			if (isSubjectValid(usuario) && isIssuerValid(emissor) && isExpirationValid(validation)) {
				return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
			}
		} catch (Exception e) {
			System.out.println("DEBUG - Erro ao decodificar o token => " + e.getMessage());
		}
		return null;

	}
}
