package com.farhad.example.springretry.exception;

public class FooException extends RuntimeException {

    public FooException() {
    }

    public FooException(String message) {
        super(message);
    }

    public FooException(Throwable cause) {
        super(cause);
    }
    
}
