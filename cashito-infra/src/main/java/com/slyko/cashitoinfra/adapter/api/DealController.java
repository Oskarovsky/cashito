package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.port.in.DealManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.DealProductsRequest;
import com.slyko.cashitoinfra.adapter.api.dto.DealRequest;
import com.slyko.cashitoinfra.adapter.api.dto.DealStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
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
            @RequestParam(value = "relations", defaultValue = "false") boolean loadRelations
    ) {
        return dealManagementPort.getDealById(id, null, loadRelations);
    }

    @PostMapping
    public Mono<Deal> createDeal(@RequestBody DealRequest request) {
        return dealManagementPort.createDeal(request.toDomainCreate());
    }

    @PutMapping(value = "/{id}")
    public Mono<Deal> update(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealRequest request
    ) {
        return dealManagementPort.updateDeal(id, version, request.toDomainUpdate());
    }

    @PatchMapping(value = "/{id}/status")
    public Mono<Deal> updateStatus(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealStatusRequest request
    ) {
        return dealManagementPort.updateDeal(id, version, request.toDomain());
    }

    @PostMapping(value = "/{id}/relationships/products")
    public Mono<Deal> addNewProducts(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealProductsRequest request
    ) {
        return dealManagementPort.updateDealProducts(id, version, request.toDomain());
    }

    @GetMapping(value = "{id}/cost")
    public Mono<BigDecimal> getDealCost(
            @PathVariable final UUID id
    ) {
        return dealManagementPort.getDealCost(id);
    }

}
