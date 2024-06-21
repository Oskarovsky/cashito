package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.Provider;
import com.slyko.cashitoapplication.port.in.ProviderManagementPort;
import com.slyko.cashitoapplication.port.out.ProviderSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
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
