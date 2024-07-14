package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.port.in.BaseManagementPort;
import com.slyko.cashitodomain.port.out.BaseSecondaryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BaseService<T, ID> implements BaseManagementPort<T, ID> {

    private final BaseSecondaryPort<T, ID> baseSecondaryPort;

    @Override
    public Flux<T> getAll() {
        return baseSecondaryPort.findAll();
    }

    @Override
    public Mono<T> getById(ID id, Long version, boolean loadRelations) {
        return baseSecondaryPort.findById(id, version, loadRelations);
    }

    @Override
    public Mono<T> create(T t) {
        return baseSecondaryPort.create(t);
    }

    @Override
    public Mono<T> update(ID id, Long version, T deal) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(ID id, Long version) {
        return baseSecondaryPort.deleteById(id, version);
    }
}
