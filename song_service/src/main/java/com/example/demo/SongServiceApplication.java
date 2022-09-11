package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Songs API", version = "1.0", description = "Song microservice"))
public class SongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongServiceApplication.class, args);
	}

	/*
	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
	 */
}
