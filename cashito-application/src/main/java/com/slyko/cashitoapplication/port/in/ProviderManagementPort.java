package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProviderManagementPort {

    Flux<Provider> getProviders();
    Mono<Provider> getProvider(UUID id);
    Mono<Provider> createProvider(Provider provider);
}
