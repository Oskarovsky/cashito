package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.in.DealManagementPort;
import com.slyko.cashitoapplication.port.out.DealsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class DealService implements DealManagementPort {

    private final DealsSecondaryPort dealsSecondaryPort;

    public Flux<Deal> getDeals() {
        return dealsSecondaryPort.findAll();
    }

    @Override
    public Mono<Deal> getDeal(UUID dealId) {
        return dealsSecondaryPort.findDealById(dealId);
    }

    @Override
    public Mono<Deal> createDeal(Deal deal) {
        return dealsSecondaryPort.createDeal(deal);
    }

    @Override
    public Mono<Payment> payDeal(UUID dealId) {
        return null;
    }
}
