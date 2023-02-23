package com.farhad.example.springretry.service;

import org.springframework.retry.annotation.Recover;
// import org.springframework.retry.annotation.Backoff;
// import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.farhad.example.springretry.exception.FooException;

public interface  RetryableWithRecoverService {
    
    @Retryable(value = FooException.class)
    public String doUppercaseActionWithRecovery(String arg) ;

    @Recover
    public String recover(FooException foo , String arg) ;
    
}
