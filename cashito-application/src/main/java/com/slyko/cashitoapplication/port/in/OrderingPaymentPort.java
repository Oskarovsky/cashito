package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.domain.Product;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface OrderingPaymentPort {

    Mono<Order> placeOrder(Order order);
//    Order updateOrder(UUID orderId, Order order);
//    void cancelOrder(UUID orderId);
    Mono<Payment> payOrder(UUID orderId);
//    Receipt readReceipt(UUID orderId);
//    Order takeOrder(UUID orderId);
}
