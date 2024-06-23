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

import static org.springframework.web.reactive.function.server.ServerResponse.noContent;

@UseCase
@RequiredArgsConstructor
public class DealService implements DealManagementPort {

    private final DealsSecondaryPort dealsSecondaryPort;

    public Flux<Deal> getDeals() {
        return dealsSecondaryPort.findAll();
    }

    @Override
    public Mono<Deal> getDealById(UUID dealId, Long version, boolean loadRelations) {
        return dealsSecondaryPort.findById(dealId, version, loadRelations);
    }

    @Override
    public Mono<Deal> createDeal(Deal deal) {
        return dealsSecondaryPort.create(deal);
    }

    @Override
    public Mono<Deal> updateDeal(UUID id, Long version, Deal deal) {
        return dealsSecondaryPort
                .findById(id, version, false)
                .flatMap(dealsSecondaryPort::update);
    }

    @Override
    public Mono<Payment> payDeal(UUID dealId) {
        return null;
    }
}
