package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.DealStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public abstract interface DealsSecondaryPort extends BaseSecondaryPort<Deal, UUID> {

    Mono<Deal> updateDealProducts(UUID id, Long version, Deal deal);
    Mono<BigDecimal> getDealCost(UUID id);
    Flux<Deal> getDealsByStatus(DealStatus status);
}
