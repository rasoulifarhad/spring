package com.farhad.example.springretry.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

import com.farhad.example.springretry.exception.BarException;
import com.farhad.example.springretry.exception.FooException;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import com.farhad.example.springretry.service.TemplateRetryService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateRetryServiceImpl implements TemplateRetryService{
    
    // private final RetryTemplate retryTemplate ;

    // @Override
    // public String doUppercaseAction(String arg) throws Throwable {
        
    //     log.info("");
    //     AtomicInteger counter = new AtomicInteger(0);

    //     return retryTemplate.execute(new RetryCallback<String,Throwable>() {

    //         @Override
    //         public String doWithRetry(RetryContext context) throws Throwable {

    //             return arg.toUpperCase();
    //         }

    //     });

    // }

    @Override
    public String doUppercaseActionWithFooException(String arg)  {
        
        log.info("Throw FooException in methode {}() ","doUppercaseAction");
        throw new FooException() ;
        // return arg.toUpperCase();
    }

    @Override
    public String doUppercaseActionWithBarException(String arg)  {
        
        log.info("Throw FooException in methode {}() ","doUppercaseAction");
        throw new BarException() ;
        // return arg.toUpperCase();
    }
}
