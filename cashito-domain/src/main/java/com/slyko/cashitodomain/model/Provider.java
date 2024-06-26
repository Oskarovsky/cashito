package com.slyko.cashitodomain.model;

import java.util.UUID;

public record Provider(
    UUID id,
    String name
) { }
