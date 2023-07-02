package com.fbs.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class MyGatewayRoute {

	@Bean
	public RouteLocator gatewayRounte(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/airline/**").uri("http://localhost:9091"))
				.route(r -> r.path("/api/flight/**").uri("http://localhost:9091"))
				.route(r -> r.path("/api/schedule/**").uri("http://localhost:9091"))
				.route(r -> r.path("/api/airport/**").uri("http://localhost:9091"))
				.route(r -> r.path("/api/location/**").uri("http://localhost:9091"))

				.route(r -> r.path("/api/consumer/**").uri("http://localhost:9092"))

				.route(r -> r.path("/api/auth/**").uri("http://localhost:9098"))

				.build();

	}
}
