package com.farhad.example.asyncmethod;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(200)
public class AsyncAppRunner implements CommandLineRunner{

    private final AsyncGitHubLookupService asyncGitHubLookupService;
    private final GitHubLookupService gitHubLookupService;


    @Override
    public void run(String... args) throws Exception {
       
        long asyncStart = System.currentTimeMillis();

        CompletableFuture<User> asyncPage1 = asyncGitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> asyncPage2 = asyncGitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> asyncPage3 = asyncGitHubLookupService.findUser("Spring-Projects");

        CompletableFuture.allOf(asyncPage1, asyncPage2, asyncPage3).join();

        log.info("Elapsed time: {}", (System.currentTimeMillis() - asyncStart));
        log.info("User: {}", asyncPage1.get());
        log.info("User: {}", asyncPage2.get());
        log.info("User: {}", asyncPage3.get());
     
        Thread.sleep(5000L);     

        long syncStart = System.currentTimeMillis();

        User syncPage1 = gitHubLookupService.findUser("PivotalSoftware");
        User syncPage2 = gitHubLookupService.findUser("CloudFoundry");
        User syncPage3 = gitHubLookupService.findUser("Spring-Projects");


        log.info("Elapsed time: {}", (System.currentTimeMillis() - syncStart));
        log.info("User: {}", syncPage1);
        log.info("User: {}", syncPage2);
        log.info("User: {}", syncPage3);


    }
    
}
