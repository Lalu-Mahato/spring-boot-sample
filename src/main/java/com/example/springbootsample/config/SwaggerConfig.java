package com.example.springbootsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI(null)
                .info(new Info().title("Dhanam APIs")
                        .description("This is Dhanam apis documentation")
                        .version("v0.0.1"));
    }

}

/***
 * return new OpenAPI()
 * .info(new Info().title("SpringShop API")
 * .description("Spring shop sample application")
 * .version("v0.0.1")
 * .license(new License().name("Apache 2.0").url("http://springdoc.org")))
 * .externalDocs(new ExternalDocumentation()
 * .description("SpringShop Wiki Documentation")
 * .url("https://springshop.wiki.github.org/docs"));
 */
