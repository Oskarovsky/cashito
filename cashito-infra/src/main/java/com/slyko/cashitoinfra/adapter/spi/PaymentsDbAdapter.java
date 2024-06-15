package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.out.PaymentsSecondaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentsDbAdapter implements PaymentsSecondaryPort {

    @Override
    public Mono<Payment> findPaymentByDealId(UUID dealId) {
        return null;
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return null;
    }
}
