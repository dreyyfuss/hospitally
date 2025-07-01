package com.hospitally.hospitally.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenAPIConfig
{

    @Bean

    public OpenAPI openAPI()
    {

        return new OpenAPI()

                .info(new Info()

                        .title("Hospital Management API")

                        .description("API documentation for Hospital Management System")

                        .version("1.0.0"));

    }

}
