package com.slyko.cashitoinfra.adapter.secondary.database.entity;

import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.DealStatus;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "deal")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class DealEntity extends BaseEntity {

    @Version
    private Long version;

    private String title;

    @Enumerated
    private DealStatus status;

    private UUID accountId;

    @Transient
    private Account account;

    @Transient
    private List<ProductEntity> products;

    public DealEntity(UUID id, Long version, String title, DealStatus status, UUID accountId, List<ProductEntity> products) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.version = version;
        this.title = title;
        this.status = status;
        this.accountId = accountId;
        this.products = products;
    }
}
