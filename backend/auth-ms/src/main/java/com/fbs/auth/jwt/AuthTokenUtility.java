package com.fbs.auth.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.fbs.auth.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthTokenUtility {

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;
	
	 @Value("${jwtCookieName}")
	  private String jwtCookie;
	
	Logger logger = LoggerFactory.getLogger(AuthTokenUtility.class);
	
	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		if (cookie != null) {
			return cookie.getValue();
		}
		else {
			return null;
		}
	}
	
	public String getJwtFromCookies(Cookie cookie) {
		if (cookie != null) {
			return cookie.getValue();
		}
		else {
			return null;
		}
	}

	public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
	    String jwt = generateTokenFromUsername(userPrincipal.getUsername());
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).sameSite("http://localhost:3000").build();
	    return cookie;
	  }
	
	  public ResponseCookie getCleanJwtCookie() {
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
	    return cookie;
	  }
	  

	  public String generateTokenFromUsername(String username) {
	    return Jwts.builder()
	        .setSubject(username)
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }
	
	public String generateToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    Date now = new Date();
	    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

	    return Jwts.builder()
	            .setSubject(userPrincipal.getUsername())
	            .setIssuedAt(new Date())
	            .setExpiration(expiryDate)
	            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

//	public String generateToken(Authentication authentication) {
//		
//		return Jwts.builder().setSubject("assignment")
//				.claim("username", authentication.getName())
//				.claim("authorities", authentication.getAuthorities())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs * 1000))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//	}
	
	public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
	
	public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
	      logger.error("Invalid JWT signature: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    }
	    return false;
	  }
	public Claims getClaim(String token) {
		return (Claims) Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	}

}