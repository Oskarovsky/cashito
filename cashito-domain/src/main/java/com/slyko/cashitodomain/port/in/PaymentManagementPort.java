package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PaymentManagementPort {

    Flux<Payment> getPayments();
    Mono<Payment> getPayment(UUID id);
    Mono<Payment> createPayment(Payment payment);
}
