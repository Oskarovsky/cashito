package com.slyko.cashitoapplication.exception;

import java.util.UUID;

public class DealNotFoundException extends NotFoundException {

    public DealNotFoundException(final UUID id) {
        super("Deal [%s] is not found".formatted(id));
    }

}