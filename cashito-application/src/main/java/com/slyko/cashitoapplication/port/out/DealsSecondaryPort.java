package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Deal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface DealsSecondaryPort {

    Mono<Deal> findDealById(UUID dealId);
    Flux<Deal> findAll();
    Mono<Deal> createDeal(Deal deal);
}
