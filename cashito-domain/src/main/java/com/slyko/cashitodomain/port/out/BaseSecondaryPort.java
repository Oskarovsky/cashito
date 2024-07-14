package com.slyko.cashitodomain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract interface BaseSecondaryPort<T, ID> {

    Flux<T> findAll();
    Mono<T> findById(ID id, Long version, boolean loadRelations);
    Mono<T> create(T t);
    Mono<T> update(ID id, Long version, T t);
    Mono<Void> deleteById(ID id, Long version);

}
