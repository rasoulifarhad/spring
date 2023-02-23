package com.farhad.example.springretry.service;

// import org.springframework.context.annotation.PropertySource;
// import org.springframework.retry.annotation.Backoff;
// import org.springframework.retry.annotation.Recover;
// import org.springframework.retry.annotation.Retryable;
// import com.farhad.example.springretry.exception.FooException;

public interface ExponentialBackoffTemplateRetryService {
    
    public String doUppercaseAction(String arg) throws Throwable;
}
