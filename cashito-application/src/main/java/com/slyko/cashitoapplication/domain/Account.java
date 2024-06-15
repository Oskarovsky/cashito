package com.slyko.cashitoapplication.domain;

import java.util.UUID;

public record Account(
    UUID id,
    String name,
    String type
) { }
