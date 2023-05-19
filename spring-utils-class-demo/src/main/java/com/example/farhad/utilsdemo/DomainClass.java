package com.example.farhad.utilsdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@Component
public class DomainClass {
    
    private final List<Map<String,Object>> listOfMap = new ArrayList<>();

    @PostConstruct
    public void setup() {
      log.info("Setup()!");  
    }

}
