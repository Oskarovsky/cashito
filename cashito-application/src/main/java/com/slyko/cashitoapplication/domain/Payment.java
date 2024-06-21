package com.slyko.cashitoapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
    UUID id,
    UUID dealId,
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime paid,
    UUID accountId,
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdDate
) { }
