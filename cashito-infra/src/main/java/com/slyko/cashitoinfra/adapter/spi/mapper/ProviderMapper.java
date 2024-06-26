package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitodomain.domain.Provider;
import com.slyko.cashitoinfra.adapter.spi.entity.ProviderEntity;

public class ProviderMapper {

    public static Provider toApi(ProviderEntity db) {
        return new Provider(
            db.getId(),
            db.getName()
        );
    }

    public static ProviderEntity toDb(Provider api) {
        return new ProviderEntity(
            api.id(),
            api.name()
        );
    }

}
