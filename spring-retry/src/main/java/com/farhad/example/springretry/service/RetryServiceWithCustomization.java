package com.farhad.example.springretry.service;

import org.springframework.retry.annotation.Backoff;
// import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import com.farhad.example.springretry.exception.FooException;


public interface RetryServiceWithCustomization {
    
    @Retryable(value = FooException.class, maxAttempts = 2 , backoff = @Backoff( delay = 100) )
    public String doUppercaseActionWithFooException(String arg) ;

    @Retryable(value = FooException.class, maxAttempts = 2 , backoff = @Backoff( delay = 100) )
    public String doUppercaseActionWithBarException(String arg) ;
}
