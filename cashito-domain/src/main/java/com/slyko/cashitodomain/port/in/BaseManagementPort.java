package com.slyko.cashitodomain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract interface BaseManagementPort<T, ID> {

    Flux<T> getAll();
    Mono<T> getById(ID id, Long version, boolean loadRelations);
    Mono<T> create(T t);
    Mono<T> update(ID id, Long version, T deal);
    Mono<Void> deleteById(ID id, Long version);
}
