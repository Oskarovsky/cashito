package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PaymentsSecondaryPort {

    Mono<Payment> findPaymentByDealId(UUID dealId);
    Mono<Payment> createPayment(Payment payment);
}
