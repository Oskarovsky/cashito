package com.slyko.cashitoinfra.adapter.secondary.mapper;

import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;

public class AccountMapper {

    public static AccountEntity toDb(Account api) {
        return new AccountEntity(
            api.getId(),
            api.getVersion(),
            api.getName(),
            api.getType()
        );
    }

    public static Account toApi(AccountEntity db) {
        return new Account(
            db.getId(),
            db.getVersion(),
            db.getName(),
            db.getType()
        );
    }
}
