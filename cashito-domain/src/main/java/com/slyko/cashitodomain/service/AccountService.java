package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.port.in.AccountManagementPort;
import com.slyko.cashitodomain.port.out.BaseSecondaryPort;
import com.slyko.cashitodomain.util.UseCase;

import java.util.UUID;

@UseCase
public class AccountService extends BaseService<Account, UUID> implements AccountManagementPort{

    public AccountService(BaseSecondaryPort<Account, UUID> baseSecondaryPort) {
        super(baseSecondaryPort);
    }


}
