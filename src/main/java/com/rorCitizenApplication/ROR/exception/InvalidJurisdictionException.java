package com.rorCitizenApplication.ROR.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJurisdictionException extends RuntimeException {
    public InvalidJurisdictionException(String message) {
        super(message);
    }
}