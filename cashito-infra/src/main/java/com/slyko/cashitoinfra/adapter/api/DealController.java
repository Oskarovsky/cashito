package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.port.in.DealManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.DealRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/deal")
@RequiredArgsConstructor
public class DealController {

    private final DealManagementPort dealManagementPort;

    @GetMapping
    public Flux<Deal> getDeals() {
        return dealManagementPort.getDeals();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Deal> getDeal(
            @PathVariable(value = "id") UUID id,
            @RequestParam(value = "version", defaultValue = "0") Long version,
            @RequestParam(value = "relations", defaultValue = "false") boolean loadRelations
    ) {
        return dealManagementPort.getDeal(id, version, loadRelations);
    }

    @PostMapping
    public Mono<Deal> createDeal(@RequestBody DealRequest request) {
        return dealManagementPort.createDeal(request.toDomain());
    }

}
