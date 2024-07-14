package com.slyko.cashitoinfra.adapter.secondary;

import com.slyko.cashitodomain.port.out.ProviderSecondaryPort;
import com.slyko.cashitodomain.model.Provider;
import com.slyko.cashitoinfra.adapter.secondary.mapper.ProviderMapper;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProviderReactiveRepository;
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
    public Flux<Provider> findAll() {
        return providerReactiveRepository
                .findAll()
                .map(ProviderMapper::toApi);
    }

    @Override
    public Mono<Provider> findById(UUID id, Long version, boolean loadRelations) {
        return providerReactiveRepository
                .findById(id)
                .map(ProviderMapper::toApi);
    }

    @Override
    public Mono<Provider> create(Provider provider) {
        return providerReactiveRepository
                .save(ProviderMapper.toDb(provider))
                .map(ProviderMapper::toApi);
    }

    @Override
    public Mono<Provider> update(UUID uuid, Long version, Provider provider) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid, Long version) {
        return null;
    }
}
