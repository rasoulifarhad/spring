package com.farhad.example.springretry.service.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farhad.example.springretry.exception.BarException;
import com.farhad.example.springretry.exception.FooException;
import com.farhad.example.springretry.service.RetryServiceWithCustomization;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Slf4j
@SpringBootTest
public class RetryServiceWithCustomizationTest {
    
    @Autowired
    private RetryServiceWithCustomization retryServiceWithCustomization;

    @Test
    public void doUppercaseActionWithFooExceptionTest() {

        log.info("");
        RuntimeException ex = assertThrows(FooException.class, () ->
                                                        retryServiceWithCustomization.doUppercaseActionWithFooException("test") );

        assertInstanceOf(FooException.class, ex);
    }

    @Test
    public void tt() {

        RuntimeException ex = assertThrows(BarException.class, () -> 
                                                        retryServiceWithCustomization.doUppercaseActionWithBarException("test") );

        assertInstanceOf(BarException.class, ex);
        
    }



}
