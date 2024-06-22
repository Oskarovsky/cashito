package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface DealManagementPort {

    Flux<Deal> getDeals();
    Mono<Deal> getDeal(UUID dealId, Long version, boolean loadRelations);
    Mono<Deal> createDeal(Deal deal);
    Mono<Payment> payDeal(UUID dealId);
}
