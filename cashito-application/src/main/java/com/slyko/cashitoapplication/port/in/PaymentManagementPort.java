package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PaymentManagementPort {

    Flux<Payment> getPayments();
    Mono<Payment> getPayment(UUID id);
    Mono<Payment> createPayment(Payment payment);
}
