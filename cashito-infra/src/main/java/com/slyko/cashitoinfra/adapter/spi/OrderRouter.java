package com.slyko.cashitoinfra.adapter.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(OrderHandler handler) {
        return route(POST("/order").and(accept(MediaType.APPLICATION_JSON)), handler::createOrder);
    }}
