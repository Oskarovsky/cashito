package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Provider;
import com.slyko.cashitoapplication.port.in.ProviderManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.ProviderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderManagementPort providerManagementPort;

    @GetMapping
    public ResponseEntity<Flux<Provider>> getProducts() {
        Flux<Provider> productFlux = providerManagementPort.getProviders();
        return new ResponseEntity<>(productFlux, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Provider>> getProduct(@PathVariable(value = "id") UUID id) {
        Mono<Provider> productMono = providerManagementPort.getProvider(id);
        return new ResponseEntity<>(productMono, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Provider>> createProduct(@RequestBody ProviderRequest request) {
        Mono<Provider> product = providerManagementPort.createProvider(request.toDomain());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
