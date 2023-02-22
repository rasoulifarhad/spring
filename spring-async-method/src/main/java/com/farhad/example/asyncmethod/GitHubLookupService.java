package com.farhad.example.asyncmethod;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GitHubLookupService {
 
    private final RestTemplate restTemplate ;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder ) {

        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public User findUser(String user) throws InterruptedException {

        log.info("Looking up {}", user);

        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);
        return results;

    }
}
