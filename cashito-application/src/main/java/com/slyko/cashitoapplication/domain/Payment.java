package com.slyko.cashitoapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
    UUID id,
    @NonNull UUID dealId,
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime paid,
    @NonNull UUID accountId
) { }
