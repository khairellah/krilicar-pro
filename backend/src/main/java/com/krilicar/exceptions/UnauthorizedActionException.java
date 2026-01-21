package com.krilicar.exceptions;

// Pour les 403
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {
        super(message);
    }

}