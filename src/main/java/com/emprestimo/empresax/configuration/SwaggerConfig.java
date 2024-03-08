package com.emprestimo.empresax.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
		
		@Bean
		public OpenAPI openApi() {
			return new OpenAPI().info(new Info().title("Api de emprestimo").description("API de emprestimo")
					.version("1.0.0").license(new License().name("Apache 2.0").url("http://springdoc.org")));

		}

     
}
