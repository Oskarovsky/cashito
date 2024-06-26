package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.in.PaymentManagementPort;
import com.slyko.cashitoapplication.port.out.PaymentsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
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
