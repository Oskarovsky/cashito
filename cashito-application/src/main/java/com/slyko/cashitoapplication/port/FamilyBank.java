package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.port.in.PreparingPaymentPort;
import com.slyko.cashitoapplication.port.out.DealsSecondaryPort;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FamilyBank implements PreparingPaymentPort {

    private final DealsSecondaryPort dealsSecondaryPort;

    public FamilyBank(DealsSecondaryPort dealsSecondaryPort) {
        this.dealsSecondaryPort = dealsSecondaryPort;
    }

    @Override
    public Mono<Deal> startPreparingDeal(UUID dealId) {
        return null;
    }

    @Override
    public Mono<Deal> finishPreparingDeal(UUID dealId) {
        return null;
    }

}
