package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.port.out.ProviderSecondaryPort;
import com.slyko.cashitodomain.domain.Provider;
import com.slyko.cashitoinfra.adapter.spi.mapper.ProviderMapper;
import com.slyko.cashitoinfra.adapter.spi.repository.ProviderReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProviderDbAdapter implements ProviderSecondaryPort {

    private final ProviderReactiveRepository providerReactiveRepository;

    @Override
    public Flux<Provider> findAllProviders() {
        return providerReactiveRepository
                .findAll()
                .map(ProviderMapper::toApi);
    }

    @Override
    public Mono<Provider> findProviderById(UUID id) {
        return providerReactiveRepository
                .findById(id)
                .map(ProviderMapper::toApi);
    }

    @Override
    public Mono<Provider> createProvider(Provider provider) {
        return providerReactiveRepository
                .save(ProviderMapper.toDb(provider))
                .map(ProviderMapper::toApi);
    }
}
