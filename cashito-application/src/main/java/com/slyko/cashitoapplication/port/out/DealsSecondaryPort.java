package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Deal;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface DealsSecondaryPort {

    Mono<Deal> findDealById(UUID dealId);
    Mono<Deal> createDeal(Deal deal);
}
