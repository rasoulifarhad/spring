package com.farhad.example.springretry.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farhad.example.springretry.exception.BarException;
import com.farhad.example.springretry.exception.FooException;
import com.farhad.example.springretry.service.RetryableService;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Slf4j
@SpringBootTest
public class RetryableServiceTest {
    void tt() {
        
    }
    @Autowired
    private RetryableService retryableService;

    @Test
    public void doUppercaseActionWithFooExceptionTest() {

        RuntimeException ex = assertThrows( FooException.class, () -> retryableService.doUppercaseActionWithFooException("test") );

        assertInstanceOf( FooException.class ,ex);
    }

    @Test
    public void doUppercaseActionWithBarExceptionTest() {

        RuntimeException ex = assertThrows(BarException.class, () -> retryableService.doUppercaseActionWithBarException("test"));
        
        assertInstanceOf(BarException.class, ex);
    }
}
