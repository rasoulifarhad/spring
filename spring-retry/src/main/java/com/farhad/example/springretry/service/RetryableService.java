package com.farhad.example.springretry.service;

// import org.springframework.retry.annotation.Backoff;
// import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.farhad.example.springretry.exception.FooException;

public interface RetryableService {
    
    @Retryable(value = FooException.class)
    public String doUppercaseActionWithFooException(String arg) ;

    @Retryable(value = FooException.class)
    public String doUppercaseActionWithBarException(String arg) ;
}
