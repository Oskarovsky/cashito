package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "account")
@Getter
@Setter
@Accessors(chain = true)
public class AccountEntity extends BaseEntity {

    private String name;
    private String type;

    public AccountEntity(UUID id, String name, String type) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.name = name;
        this.type = type;
    }

    public static AccountEntity toDb(Account account) {
        return new AccountEntity(account.getId(), account.getName(), account.getType());
    }

    public Account toApi() {
        return new Account(getId(), name, type);
    }
}
