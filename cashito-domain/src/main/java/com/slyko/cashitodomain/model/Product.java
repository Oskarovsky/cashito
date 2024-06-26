package com.slyko.cashitodomain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
    UUID id,
    String name,
    ProductType type,
    BigDecimal cost,
    UUID dealId,
    UUID providerId
) { }
