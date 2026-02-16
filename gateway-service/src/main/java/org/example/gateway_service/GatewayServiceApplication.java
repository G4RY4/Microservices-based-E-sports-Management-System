package org.example.gateway_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${esport.teams.url}") String teamsUrl,
			@Value("${esport.players.url}") String playersUrl,
			@Value("${esport.gateway.host}") String host
	) {
		return builder
				.routes()

				.route("players-nested", route -> route
						.host(host)
						.and()
						.path(
								"/api/teams/{uuid}/players",
								"/api/teams/{uuid}/players/**"
						)
						.uri(playersUrl)
				)

				.route("teams-service", route -> route
						.host(host)
						.and()
						.path(
								"/api/teams",
								"/api/teams/**"
						)
						.uri(teamsUrl)
				)

				.route("players-service", route -> route
						.host(host)
						.and()
						.path(
								"/api/players",
								"/api/players/**"
						)
						.uri(playersUrl)
				)
				.build();
	}
}
