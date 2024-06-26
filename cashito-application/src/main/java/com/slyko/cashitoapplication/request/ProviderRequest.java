package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Provider;

public record ProviderRequest(String name) {

    public Provider toDomain() {
        return new Provider(
            null,
                name
        );
    }
}
