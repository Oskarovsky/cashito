package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.Payment;
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
