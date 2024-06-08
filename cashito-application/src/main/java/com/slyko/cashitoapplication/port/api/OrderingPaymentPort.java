package com.slyko.cashitoapplication.port.api;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.domain.Receipt;

import java.util.UUID;

public abstract interface OrderingPaymentPort {

    Order placeOrder(Order order);
//    Order updateOrder(UUID orderId, Order order);
//    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId);
//    Receipt readReceipt(UUID orderId);
//    Order takeOrder(UUID orderId);
}
