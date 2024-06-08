package com.slyko.cashitoapplication.port.spi;

import com.slyko.cashitoapplication.domain.Payment;

import java.util.UUID;

public abstract interface PaymentsSecondaryPort {

    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
