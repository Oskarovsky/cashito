package com.slyko.cashitodomain.domain;

import java.util.UUID;

public record Provider(
    UUID id,
    String name
) { }
