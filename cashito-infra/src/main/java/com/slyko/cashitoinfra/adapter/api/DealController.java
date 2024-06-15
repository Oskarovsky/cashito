package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.port.in.DealPaymentPort;
import com.slyko.cashitoinfra.adapter.api.dto.DealRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/deal")
@RequiredArgsConstructor
public class DealController {

    private final DealPaymentPort dealPaymentPort;

    @PostMapping
    public Mono<Deal> createDeal(@RequestBody DealRequest request) {
        return dealPaymentPort.createDeal(request.toDomain());
    }

}
