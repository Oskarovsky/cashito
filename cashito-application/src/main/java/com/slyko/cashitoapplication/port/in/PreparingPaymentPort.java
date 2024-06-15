package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Deal;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PreparingPaymentPort {

    Mono<Deal> startPreparingDeal(UUID dealId);
    Mono<Deal> finishPreparingDeal(UUID dealId);


}
