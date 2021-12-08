package com.tis.in.BanX.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tis.in.BanX")).build().apiInfo(apiInfo())
				.securitySchemes(Arrays.asList(apiKey()));
		// .apiInfo(metaData());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Sig-Predict REST API Document").description("work in progress")
				.termsOfServiceUrl("localhost").version("1.0").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

}