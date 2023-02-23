package com.farhad.example.springretry.service.impl;

import org.springframework.stereotype.Service;
import com.farhad.example.springretry.exception.FooException;
import com.farhad.example.springretry.service.RetryableWithRecoverService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RetryableWithRecoverServiceImpl implements RetryableWithRecoverService {

    @Override
    public String doUppercaseActionWithRecovery(String arg) {
        log.info("Throw FooException in methode {} ","doUppercaseActionWithRecovery");
        throw new FooException();
        // return arg.toUpperCase();
    }

    @Override
    public String recover(FooException foo, String arg) {
        log.info("Recovery for FooException in methode {} ","doUppercaseActionWithRecovery");
        return arg.toUpperCase();
    }
    
}
