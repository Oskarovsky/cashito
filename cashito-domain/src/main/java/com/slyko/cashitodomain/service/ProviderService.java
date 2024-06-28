package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.port.in.ProviderManagementPort;
import com.slyko.cashitodomain.port.out.ProviderSecondaryPort;
import com.slyko.cashitodomain.model.Provider;
import com.slyko.cashitodomain.util.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class ProviderService implements ProviderManagementPort {

    private final ProviderSecondaryPort providerSecondaryPort;

    @Override
    public Flux<Provider> getProviders() {
        return providerSecondaryPort.findAllProviders();
    }

    @Override
    public Mono<Provider> getProvider(UUID id) {
        return providerSecondaryPort.findProviderById(id);
    }

    @Override
    public Mono<Provider> createProvider(Provider provider) {
        return providerSecondaryPort.createProvider(provider);
    }
}
