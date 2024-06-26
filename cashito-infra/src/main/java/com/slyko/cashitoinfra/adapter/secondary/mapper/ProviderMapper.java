package com.slyko.cashitoinfra.adapter.secondary.mapper;

import com.slyko.cashitodomain.model.Provider;
import com.slyko.cashitoinfra.adapter.secondary.entity.ProviderEntity;

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
