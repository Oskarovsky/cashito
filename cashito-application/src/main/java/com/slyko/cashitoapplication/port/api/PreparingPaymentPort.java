package com.slyko.cashitoapplication.port.api;

import com.slyko.cashitoapplication.domain.Order;

import java.util.UUID;

public abstract interface PreparingPaymentPort {

    Order startPreparingOrder(UUID orderId);
//    Order finishPreparingOrder(UUID orderId);


}
