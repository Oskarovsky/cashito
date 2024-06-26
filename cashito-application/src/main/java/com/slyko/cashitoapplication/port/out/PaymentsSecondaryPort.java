package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PaymentsSecondaryPort {

    Flux<Payment> findAll();
    Mono<Payment> findById(UUID id);
    Flux<Payment> findPaymentsByDealId(UUID dealId);
    Mono<Payment> createPayment(Payment payment);
}
