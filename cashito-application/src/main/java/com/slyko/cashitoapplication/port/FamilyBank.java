package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.api.PreparingPaymentPort;
import com.slyko.cashitoapplication.port.spi.OrdersSecondaryPort;

import java.util.UUID;

public class FamilyBank implements PreparingPaymentPort {

    private final OrdersSecondaryPort ordersSecondaryPort;

    public FamilyBank(OrdersSecondaryPort ordersSecondaryPort) {
        this.ordersSecondaryPort = ordersSecondaryPort;
    }

    @Override
    public Order startPreparingOrder(UUID orderId) {
        var order = ordersSecondaryPort.findOrderById(orderId);

        return ordersSecondaryPort.save(order.markBeingPrepared());
    }

}
