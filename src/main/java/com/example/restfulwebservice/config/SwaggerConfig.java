package com.example.restfulwebservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Bikoo's First Swagger")
                .version("ver 1.0.0")
                .description("Spring Doc를 활용한 스웨거 구현");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}