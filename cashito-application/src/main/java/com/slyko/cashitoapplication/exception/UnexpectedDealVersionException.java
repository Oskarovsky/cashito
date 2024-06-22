package com.slyko.cashitoapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class UnexpectedDealVersionException extends NotFoundException {

    public UnexpectedDealVersionException(Long expectedVersion, Long foundVersion) {
        super("The deal has a different version than the expected one. Expected [%s], found [%s]".
                formatted(expectedVersion, foundVersion));
    }

}