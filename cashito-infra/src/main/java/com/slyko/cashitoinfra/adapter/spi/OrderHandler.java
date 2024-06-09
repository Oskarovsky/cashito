package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.in.OrderingPaymentPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    private final OrderingPaymentPort orderingPaymentPort;

    public OrderHandler(OrderingPaymentPort orderingPaymentPort) {
        this.orderingPaymentPort = orderingPaymentPort;
    }

    public Mono<ServerResponse> createOrder(ServerRequest request) {
        return request.bodyToMono(Order.class)
                .flatMap(order -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(orderingPaymentPort.placeOrder(order), Order.class)
                );
    }
}
