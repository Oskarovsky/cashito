package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Provider;

public record ProviderRequest(String name) {

    public Provider toDomain() {
        return new Provider(
            null,
                name
        );
    }
}
