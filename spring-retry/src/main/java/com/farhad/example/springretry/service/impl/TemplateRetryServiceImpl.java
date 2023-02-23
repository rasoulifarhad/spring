package com.farhad.example.springretry.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import com.farhad.example.springretry.service.TemplateRetryService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateRetryServiceImpl implements TemplateRetryService{
    
    private final RetryTemplate retryTemplate ;

    @Override
    public String doUppercaseAction(String arg) throws Throwable {
        
        log.info("");

        return retryTemplate.execute(new RetryCallback<String,Throwable>() {

            @Override
            public String doWithRetry(RetryContext context) throws Throwable {
                return arg.toUpperCase();
            }

        });

    }
}
