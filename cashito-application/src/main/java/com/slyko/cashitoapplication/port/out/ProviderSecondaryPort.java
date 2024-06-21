package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProviderSecondaryPort {

    Flux<Provider> findAllProviders();
    Mono<Provider> findProviderById(UUID providerId);
    Mono<Provider> createProvider(Provider provider);
}
