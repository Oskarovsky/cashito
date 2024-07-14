package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Payment;
import reactor.core.publisher.Flux;

import java.util.UUID;

public abstract interface PaymentsSecondaryPort extends BaseSecondaryPort<Payment, UUID> {

    Flux<Payment> findPaymentsByDealId(UUID dealId);
}
