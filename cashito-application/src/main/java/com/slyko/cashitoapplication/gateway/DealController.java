package com.slyko.cashitoapplication.gateway;

import com.slyko.cashitoapplication.request.DealProductsRequest;
import com.slyko.cashitoapplication.request.DealRequest;
import com.slyko.cashitoapplication.request.DealStatusRequest;
import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitodomain.port.in.DealManagementPort;
import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.port.in.ProductManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping(value = "/deal")
@RequiredArgsConstructor
public class DealController {

    private final DealManagementPort dealManagementPort;
    private final ProductManagementPort productManagementPort;

    @GetMapping
    public Flux<Deal> getDeals() {
        return dealManagementPort.getAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Deal> getDeal(
            @PathVariable(value = "id") UUID id,
            @RequestParam(value = "relations", defaultValue = "false") boolean loadRelations
    ) {
        return dealManagementPort.getById(id, null, loadRelations);
    }

    @PostMapping
    public Mono<Deal> createDeal(@RequestBody DealRequest request) {
        return dealManagementPort.create(request.toDomainCreate());
    }

    @PutMapping(value = "/{id}")
    public Mono<Deal> update(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealRequest request
    ) {
        return dealManagementPort.update(id, version, request.toDomainUpdate());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDeal(
            @PathVariable UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version) {
        return dealManagementPort
                .deleteById(id, version)
                .then(Mono.fromCallable(() -> noContent().build()));
    }

    @PatchMapping(value = "/{id}/status")
    public Mono<Deal> updateStatus(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealStatusRequest request
    ) {
        return dealManagementPort.update(id, version, request.toDomain());
    }

    @PostMapping(value = "/{id}/relationships/products")
    public Mono<Deal> addNewProducts(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final DealProductsRequest request
    ) {
        return dealManagementPort.updateDealProducts(id, version, request.toDomain());
    }

    @GetMapping(value = "/{id}/cost")
    public Mono<BigDecimal> getDealCost(
            @PathVariable final UUID id
    ) {
        return dealManagementPort.getDealCost(id);
    }

    @GetMapping(value = "/{id}/relationships/products")
    public Flux<Product> getDealProducts(
            @PathVariable final UUID id
    ) {
        return productManagementPort.getDealProducts(id);
    }

}
