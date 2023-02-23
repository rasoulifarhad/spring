package com.farhad.example.springretry.service.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.farhad.example.springretry.service.RetryableWithRecoverService;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;

// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Slf4j
@SpringBootTest
public class RetryableWithRecoverServiceTest {
    
    @Autowired
    private RetryableWithRecoverService retryableWithRecoverService;

    @Test
    public void tt() {
        log.info("");
        String result = retryableWithRecoverService.doUppercaseActionWithRecovery("test");

        assertEquals("TEST", result);
    }
}
