package com.farhad.example.springretry.service.impl;

import org.springframework.stereotype.Service;

import com.farhad.example.springretry.service.ExponentialBackoffTemplateRetryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExponentialBackoffTemplateRetryServiceImple implements ExponentialBackoffTemplateRetryService{
    
    private final RetryTemplate retryTemplateBasedOnExponentialBackoff;

    @Override
    public String doUppercaseAction(String arg) throws Throwable{

        log.info("");

        return  retryTemplateBasedOnExponentialBackoff.execute(new RetryCallback<String,Throwable>() {

            @Override
            public String doWithRetry(RetryContext context) throws Throwable {
                return arg.toUpperCase();
            }
            
        });

    }
}
