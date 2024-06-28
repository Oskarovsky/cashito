package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.port.in.DealManagementPort;
import com.slyko.cashitodomain.port.out.DealsSecondaryPort;
import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.Payment;
import com.slyko.cashitodomain.util.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

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
        if (deal.getId() != null && !Objects.equals(id.toString(), deal.getId().toString())) {
            throw new IllegalArgumentException(
                    "Deal id [%s] from url must be the same as body request [%s]".formatted(id, deal.getId()));
        }
        return dealsSecondaryPort.update(id, version, deal);
    }

    @Override
    public Mono<Deal> updateDealProducts(UUID id, Long version, Deal deal) {
        if (deal.getId() != null && !Objects.equals(id.toString(), deal.getId().toString())) {
            throw new IllegalArgumentException(
                    "Deal id [%s] from url must be the same as body request [%s]".formatted(id, deal.getId()));
        }
        return dealsSecondaryPort.updateDealProducts(id, version, deal);
    }

    @Override
    public Mono<BigDecimal> getDealCost(UUID id) {
        return dealsSecondaryPort.getDealCost(id);
    }


    @Override
    public Mono<Payment> payDeal(UUID dealId) {
        return null;
    }
}
