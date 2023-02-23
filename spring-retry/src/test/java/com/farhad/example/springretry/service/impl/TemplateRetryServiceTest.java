package com.farhad.example.springretry.service.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farhad.example.springretry.exception.FooException;
import com.farhad.example.springretry.exception.BarException;

import com.farhad.example.springretry.service.TemplateRetryService;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class TemplateRetryServiceTest {

    @Autowired
    private  RetryTemplate retryTemplate ;

    @Autowired 
    private TemplateRetryService templateRetryService;

    @Test
    public void doUppercaseActionWithFooException() throws Throwable {

        log.info("");
        AtomicInteger counter = new AtomicInteger(0);

        RuntimeException ex = assertThrows(FooException.class, () -> 
                                        retryTemplate.execute(new RetryCallback<String,Throwable>() {

                                            @Override
                                            public String doWithRetry(RetryContext context) throws Throwable {
                                                counter.incrementAndGet();
                                                return templateRetryService.doUppercaseActionWithFooException("test");
                                            }

                                        })
                                );
        
        log.info("Retry # is: {}", counter.get() );
        assertInstanceOf( FooException.class ,ex);
        assertThat(counter.get()).isEqualTo(5);
    }

    @Test
    public void doUppercaseActionWithBarException() throws Throwable {

        log.info("");
        AtomicInteger counter = new AtomicInteger(0);

        RuntimeException ex = assertThrows(BarException.class, () -> 
                                        retryTemplate.execute(new RetryCallback<String,Throwable>() {

                                            @Override
                                            public String doWithRetry(RetryContext context) throws Throwable {
                                                counter.incrementAndGet();
                                                return templateRetryService.doUppercaseActionWithBarException("test");
                                            }

                                        })
                                );
        
        log.info("Retry # is: {}", counter.get() );
        assertInstanceOf( BarException.class ,ex);
        assertThat(counter.get()).isEqualTo(1);
    }

    @Test
    public void doUppercaseActionWithFooException2() throws Throwable {

        log.info("");
        RetryTemplate retryTemplate =RetryTemplate.builder()
                                                    .exponentialBackoff(500L, 2, 5000L)
                                                    // .maxAttempts(3)
                                                    .retryOn(FooException.class)
                                                    .build();

        AtomicInteger counter = new AtomicInteger(0);

        RuntimeException ex = assertThrows(FooException.class, () -> 
                                        retryTemplate.execute(new RetryCallback<String,Throwable>() {

                                            @Override
                                            public String doWithRetry(RetryContext context) throws Throwable {
                                                counter.incrementAndGet();
                                                return templateRetryService.doUppercaseActionWithFooException("test");
                                            }

                                        })
                                );
        
        log.info("Retry # is: {}", counter.get() );
        assertInstanceOf( FooException.class ,ex);
        assertThat(counter.get()).isEqualTo(3);
    }


}
