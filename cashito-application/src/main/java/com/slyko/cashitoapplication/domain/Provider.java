package com.slyko.cashitoapplication.domain;

import java.util.UUID;

public record Provider(
    UUID id,
    String name
) { }
