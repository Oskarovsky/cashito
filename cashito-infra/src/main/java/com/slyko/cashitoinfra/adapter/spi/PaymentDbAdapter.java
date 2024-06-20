package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.out.PaymentsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.mapper.PaymentMapper;
import com.slyko.cashitoinfra.adapter.spi.repository.PaymentReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentDbAdapter implements PaymentsSecondaryPort {

    private final PaymentReactiveRepository paymentReactiveRepository;

    @Override
    public Flux<Payment> findAll() {
        return paymentReactiveRepository
                .findAll()
                .map(PaymentMapper::toApi);
    }

    @Override
    public Mono<Payment> findPaymentByDealId(UUID dealId) {
        return paymentReactiveRepository
                .findById(dealId)
                .map(PaymentMapper::toApi);
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return paymentReactiveRepository
                .save(PaymentMapper.toDb(payment))
                .map(PaymentMapper::toApi);
    }
}
