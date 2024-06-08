package com.slyko.cashitoapplication.domain;

import java.math.BigDecimal;

public record Product(
    String name,
    ProductType type,
    BigDecimal cost
) { }
