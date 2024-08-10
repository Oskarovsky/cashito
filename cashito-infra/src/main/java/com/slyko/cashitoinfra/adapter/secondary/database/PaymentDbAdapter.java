package com.slyko.cashitoinfra.adapter.secondary.database;

import com.slyko.cashitodomain.port.out.PaymentsSecondaryPort;
import com.slyko.cashitodomain.model.Payment;
import com.slyko.cashitoinfra.adapter.secondary.database.mapper.PaymentMapper;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.PaymentReactiveRepository;
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
    public Mono<Payment> findById(UUID id, Long version, boolean loadRelations) {
        return paymentReactiveRepository
                .findById(id)
                .map(PaymentMapper::toApi);
    }

    @Override
    public Flux<Payment> findPaymentsByDealId(UUID dealId) {
        return paymentReactiveRepository
                .findByDealId(dealId)
                .map(PaymentMapper::toApi);
    }

    @Override
    public Mono<Payment> create(Payment payment) {
        return paymentReactiveRepository
                .save(PaymentMapper.toDb(payment))
                .map(PaymentMapper::toApi);
    }

    @Override
    public Mono<Payment> update(UUID uuid, Long version, Payment payment) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid, Long version) {
        return null;
    }
}
