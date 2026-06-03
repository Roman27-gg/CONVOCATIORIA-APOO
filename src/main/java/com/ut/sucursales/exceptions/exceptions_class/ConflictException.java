package com.ut.sucursales.exceptions.exceptions_class;

public class ConflictException extends RuntimeException {
    public ConflictException() {
    }
    public ConflictException(String message) {
        super(message);
    }
}
