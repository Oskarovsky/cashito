package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.port.in.PaymentManagementPort;
import com.slyko.cashitodomain.port.out.PaymentsSecondaryPort;
import com.slyko.cashitodomain.model.Payment;
import com.slyko.cashitodomain.util.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class PaymentService implements PaymentManagementPort {

    private final PaymentsSecondaryPort paymentsSecondaryPort;

    @Override
    public Flux<Payment> getPayments() {
        return paymentsSecondaryPort.findAll();
    }

    @Override
    public Mono<Payment> getPayment(UUID id) {
        return paymentsSecondaryPort.findById(id);
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return paymentsSecondaryPort.createPayment(payment);
    }
}
