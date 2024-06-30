package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Deal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public abstract interface DealsSecondaryPort {

    Mono<Deal> findById(UUID dealId, Long version, boolean loadRelations);
    Flux<Deal> findAll();
    Mono<Deal> create(Deal deal);
    Mono<Deal> update(UUID id, Long version, Deal deal);
    Mono<Void> delete(UUID id, Long version);
    Mono<Deal> updateDealProducts(UUID id, Long version, Deal deal);
    Mono<BigDecimal> getDealCost(UUID id);
}
