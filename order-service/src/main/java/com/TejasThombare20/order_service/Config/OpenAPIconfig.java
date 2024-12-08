package com.TejasThombare20.order_service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIconfig {
    @Bean
    public OpenAPI productServiceAPI(){

    return new OpenAPI().info(new Info().title("Order Service API")
    .description("This is the REST API for Order Service"));
 }
}
