package com.farhad.example.springretry.retryConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {
    void tt() {
        
    }

    @Bean
    public RetryTemplate retryTemplate() { 

        RetryTemplate template = new RetryTemplate();

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000L);

        template.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(2);

        template.setRetryPolicy(retryPolicy);

        return template ;
    }

    @Bean
    public RetryTemplate retryTemplateBasedOnExponentialBackoff() { 

        RetryTemplate  template = new RetryTemplate();

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(300L);
        backOffPolicy.setMultiplier(3);
        backOffPolicy.setMaxInterval(2000L);

        template.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(7);

        template.setRetryPolicy(retryPolicy);


        return template ;

    }


    
}
