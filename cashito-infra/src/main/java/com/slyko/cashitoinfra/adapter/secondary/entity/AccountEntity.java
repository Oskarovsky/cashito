package com.slyko.cashitoinfra.adapter.secondary.entity;

import com.slyko.cashitodomain.model.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "account")
@Getter
@Setter
@Accessors(chain = true)
public class AccountEntity extends BaseEntity {

    @Version
    private Long version;

    private String name;

    private AccountType type;

    public AccountEntity(UUID id, Long version, String name, AccountType type) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.name = name;
        this.type = type;
    }
}
