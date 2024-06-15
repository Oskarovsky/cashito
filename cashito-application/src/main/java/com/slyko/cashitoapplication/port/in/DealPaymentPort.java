package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface DealPaymentPort {

    Mono<Deal> createDeal(Deal deal);
    Mono<Payment> payDeal(UUID dealId);
}
