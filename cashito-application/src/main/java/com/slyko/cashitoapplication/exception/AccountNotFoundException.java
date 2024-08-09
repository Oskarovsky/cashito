package com.slyko.cashitoapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends NotFoundException {

    public AccountNotFoundException(final UUID id) {
        super("Account [%s] is not found".formatted(id));
    }
}