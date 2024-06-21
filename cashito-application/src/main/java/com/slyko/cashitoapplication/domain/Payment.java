package com.slyko.cashitoapplication.domain;

import java.time.LocalDate;
import java.util.UUID;

public record Payment(
    UUID id,
    UUID dealId,
    LocalDate paid,
    UUID accountId
) { }
