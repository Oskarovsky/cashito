package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Order;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PreparingPaymentPort {

    Mono<Order> startPreparingOrder(UUID orderId);
    Mono<Order> finishPreparingOrder(UUID orderId);


}
