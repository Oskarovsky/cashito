package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitodomain.domain.Account;
import com.slyko.cashitoinfra.adapter.spi.entity.AccountEntity;

public class AccountMapper {

    public static AccountEntity toDb(Account api) {
        return new AccountEntity(
            api.getId(),
            api.getName(),
            api.getType()
        );
    }

    public static Account toApi(AccountEntity db) {
        return new Account(
            db.getId(),
            db.getName(),
            db.getType()
        );
    }
}
