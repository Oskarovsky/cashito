package com.slyko.cashitoinfra.adapter.spi.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;
}
