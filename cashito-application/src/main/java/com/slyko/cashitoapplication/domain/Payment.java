package com.slyko.cashitoapplication.domain;

import java.time.LocalDate;
import java.util.UUID;

public record Payment(UUID orderId, LocalDate paid) { }
