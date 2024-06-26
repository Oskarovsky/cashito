package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public abstract interface DealManagementPort {

    Flux<Deal> getDeals();
    Mono<Deal> getDealById(UUID dealId, Long version, boolean loadRelations);
    Mono<Deal> createDeal(Deal deal);
    Mono<Deal> updateDeal(UUID id, Long version, Deal deal);
    Mono<Deal> updateDealProducts(UUID id, Long version, Deal deal);
    Mono<BigDecimal> getDealCost(UUID id);
    Mono<Payment> payDeal(UUID dealId);
}
