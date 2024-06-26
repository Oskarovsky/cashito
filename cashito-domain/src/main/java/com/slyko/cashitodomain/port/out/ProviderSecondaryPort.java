package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProviderSecondaryPort {

    Flux<Provider> findAllProviders();
    Mono<Provider> findProviderById(UUID providerId);
    Mono<Provider> createProvider(Provider provider);
}
