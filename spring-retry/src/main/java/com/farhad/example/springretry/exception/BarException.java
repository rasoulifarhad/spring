package com.farhad.example.springretry.exception;

public class BarException extends RuntimeException{

    public BarException() {
    }

    public BarException(String message) {
        super(message);
    }

    public BarException(Throwable cause) {
        super(cause);
    }
    
}
