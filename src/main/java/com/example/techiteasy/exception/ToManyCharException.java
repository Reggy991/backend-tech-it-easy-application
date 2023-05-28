package com.example.techiteasy.exception;

public class ToManyCharException extends RuntimeException {

    public ToManyCharException() {
        super();
    }

    public ToManyCharException(String message) {
        super(message);
    }
}
