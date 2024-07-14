package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.model.Provider;
import com.slyko.cashitodomain.port.in.ProviderManagementPort;
import com.slyko.cashitodomain.port.out.ProviderSecondaryPort;
import com.slyko.cashitodomain.util.UseCase;

import java.util.UUID;

@UseCase
public class ProviderService extends BaseService<Provider, UUID> implements ProviderManagementPort {

    private final ProviderSecondaryPort providerSecondaryPort;

    public ProviderService(ProviderSecondaryPort providerSecondaryPort) {
        super(providerSecondaryPort);
        this.providerSecondaryPort = providerSecondaryPort;
    }
}
