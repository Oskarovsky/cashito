package com.slyko.cashitoapplication.port.spi;

import com.slyko.cashitoapplication.domain.Order;

import java.util.UUID;

public abstract interface OrdersSecondaryPort {

    Order findOrderById(UUID orderId);
    Order save(Order order);
    void deleteById(UUID orderId);
}
