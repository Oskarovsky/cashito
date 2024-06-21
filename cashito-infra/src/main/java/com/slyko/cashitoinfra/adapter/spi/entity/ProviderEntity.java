package com.slyko.cashitoinfra.adapter.spi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "provider")
@Getter
@Setter
@NoArgsConstructor
public class ProviderEntity extends BaseEntity {

    private String name;

    public ProviderEntity(UUID id, String name) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.name = name;
    }
}
