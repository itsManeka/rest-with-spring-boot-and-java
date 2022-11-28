package br.com.itsmaneka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("RESTful API with JAVA 16 and Spring Boot 3")
                .version("v1")
                .description("Descrição teste")
                .termsOfService("https://pub.itsmaneka.com.br/termos")
                .license(new License().name("Apache 2.0")
                    .url("https://pub.itsmaneka.com.br/licenca")));
    }    
}
