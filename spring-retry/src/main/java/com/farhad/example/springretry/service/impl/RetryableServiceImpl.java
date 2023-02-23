package com.farhad.example.springretry.service.impl;

import org.springframework.stereotype.Service;
import com.farhad.example.springretry.exception.FooException;
import com.farhad.example.springretry.service.RetryableService;
import com.farhad.example.springretry.exception.BarException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RetryableServiceImpl implements RetryableService {
    
    @Override
    public String doUppercaseActionWithFooException(String arg) {
        log.info("Throw FooException in methode {}() ","doUppercaseActionWithFooException");
        throw new FooException();
        // return arg.toUpperCase();
    }

    @Override
    public String doUppercaseActionWithBarException(String arg) {
        log.info("Throw BarException in methode {}() ","doUppercaseActionWithBarException");
        throw new BarException();
        // return arg.toUpperCase();
    }

}
