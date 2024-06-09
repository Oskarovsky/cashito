package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Order;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface OrdersSecondaryPort {

    Mono<Order> findOrderById(UUID orderId);
    Mono<Order> createOrder(Order order);
    void deleteOrderById(UUID orderId);
}
