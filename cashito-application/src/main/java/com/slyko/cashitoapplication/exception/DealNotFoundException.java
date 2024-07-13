package com.slyko.cashitoapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DealNotFoundException extends NotFoundException {

    public DealNotFoundException(final UUID id) {
        super("Deal [%s] is not found".formatted(id));
    }

}