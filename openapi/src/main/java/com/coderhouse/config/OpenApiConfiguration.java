package com.coderhouse.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI config() {
        return new OpenAPI()
                .components(new Components())
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("Restaurantes API")
                .description("Ejemplo de redis y OpenAPI");
    }
}
