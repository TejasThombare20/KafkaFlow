package com.TejasThombare20.apigateway.Routes;

import java.net.URI;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> ProductServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product_service_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order_service_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> InventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory_service_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRouter() {
        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product_service_swagger_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderServiceSwaggerRouter() {
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order_service_swagger_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRouter() {
        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/invemtory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory_service_swagger_circuit_breaker" , URI.create("forward:/fallbackroute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRouter() {
        return route("fallback route")
                .GET("/fallbackroute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Service unavailable , please try again"))
                .build();
    }
}
