package com.fbs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fbs.auth.jwt.AuthTokenFilter;
import com.fbs.auth.jwt.EntryPointJwt;
import com.fbs.auth.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	AuthTokenFilter filter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	EntryPointJwt entryPoint;

	@Bean
	public SecurityFilterChain doFilter(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(entryPoint).and()
		.authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
		.requestMatchers("/swagger-ui/**").permitAll()
		.requestMatchers("/api/airline/**").hasAnyRole("ADMIN")
	

				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}


}
