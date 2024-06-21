package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.port.in.DealManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.DealRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/deal")
@RequiredArgsConstructor
public class DealController {

    private final DealManagementPort dealManagementPort;

    @GetMapping
    public Flux<Deal> getDeals() {
        return dealManagementPort.getDeals();
    }

    @GetMapping("/{id}")
    public Mono<Deal> getDeal(@PathVariable(value = "id") UUID id) {
        return dealManagementPort.getDeal(id);
    }

    @PostMapping
    public Mono<Deal> createDeal(@RequestBody DealRequest request) {
        return dealManagementPort.createDeal(request.toDomain());
    }

}
