package com.slyko.cashitodomain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract interface BaseSecondaryPort<T, ID> {

    Flux<T> getAll();
    Mono<T> getById(ID productId, Long version, boolean loadRelations);
    Mono<T> create(T t);
    Mono<Void> deleteById(ID id, Long version);

}
