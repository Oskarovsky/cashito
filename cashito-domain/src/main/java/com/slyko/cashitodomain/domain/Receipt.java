package com.slyko.cashitodomain.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Receipt(BigDecimal amount, LocalDate paid) { }
