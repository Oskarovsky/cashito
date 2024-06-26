package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitodomain.domain.Account;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "payment")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {

    @Column(name = "deal_id")
    UUID dealId;

    @Column(columnDefinition = "DATE")
    LocalDateTime paid;

    private UUID accountId;

    @Transient
    private Account account;

    public PaymentEntity(UUID id, UUID dealId, LocalDateTime paid, UUID account) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.dealId = dealId;
        this.paid = paid;
        this.accountId = account;
    }
}
