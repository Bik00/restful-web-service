package com.example.restfulwebservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Contact contact = new Contact()
                .name("Bikoo")
                .url("www.github.com/Bik00")
                .email("bikoo3002@naver.com");

        License license = new License()
                .url("http://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("Bikoo's First Swagger")
                .version("ver 1.0.0")
                .contact(contact)
                .description("Spring Doc를 활용한 스웨거 구현")
                .license(license);

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}