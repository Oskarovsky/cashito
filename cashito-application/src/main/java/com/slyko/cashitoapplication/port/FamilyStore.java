package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.api.OrderingPaymentPort;
import com.slyko.cashitoapplication.port.spi.OrdersSecondaryPort;
import com.slyko.cashitoapplication.port.spi.PaymentsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class FamilyStore implements OrderingPaymentPort {

    private final OrdersSecondaryPort ordersSecondaryPort;
    private final PaymentsSecondaryPort paymentsSecondaryPort;

    public FamilyStore(OrdersSecondaryPort ordersSecondaryPort, PaymentsSecondaryPort paymentsSecondaryPort) {
        this.ordersSecondaryPort = ordersSecondaryPort;
        this.paymentsSecondaryPort = paymentsSecondaryPort;
    }

    @Override
    public Order placeOrder(Order order) {
        return null;
    }

    @Override
    public Payment payOrder(UUID orderId) {
        var order = ordersSecondaryPort.findOrderById(orderId);

        ordersSecondaryPort.save(order.markPaid());

        return paymentsSecondaryPort.save(new Payment(orderId, LocalDate.now()));
    }
}
