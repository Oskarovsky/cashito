package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.in.PreparingPaymentPort;
import com.slyko.cashitoapplication.port.out.OrdersSecondaryPort;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FamilyBank implements PreparingPaymentPort {

    private final OrdersSecondaryPort ordersSecondaryPort;

    public FamilyBank(OrdersSecondaryPort ordersSecondaryPort) {
        this.ordersSecondaryPort = ordersSecondaryPort;
    }

    @Override
    public Mono<Order> startPreparingOrder(UUID orderId) {
        var order = ordersSecondaryPort.findOrderById(orderId);
        return null;
//        return ordersSecondaryPort.save(order.markBeingPrepared());
    }

    @Override
    public Mono<Order> finishPreparingOrder(UUID orderId) {
        return null;
    }

}
