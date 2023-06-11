package com.fbs.auth.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fbs.auth.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private AuthTokenUtility jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	// Logger log = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.debug("=============== filter =================");
//		  logger.debug(request.getHeaderNames());

		try {
			String jwt = parseJwt(request);
			logger.info("====================jwt:"+jwt+"======================");
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUsernameFromToken(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Cannot set user authentication: {}", e);
		}

//		String token = request.getHeader("Authorization");
////		  String token =null;
//		if (token != null && token.startsWith("Bearer ")) {
//			token = token.substring(7);
//			if (!jwtUtils.validateToken(token)) {
//				logger.debug("=============== valid token =================");
//				String username = jwtUtils.getUsernameFromToken(token);
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetailsService.loadUserByUsername(username), null,
//						userDetailsService.loadUserByUsername(username).getAuthorities());
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//			} else {
//				logger.debug("=============== invalid token =================");
//			}
//		} else {
//			logger.debug("=============== token is null =================");
//		}
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		return jwt;
	}

}
