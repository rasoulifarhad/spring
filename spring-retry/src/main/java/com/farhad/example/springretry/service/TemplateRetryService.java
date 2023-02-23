package com.farhad.example.springretry.service;

public interface TemplateRetryService {
    
    public String doUppercaseActionWithFooException(String arg);
    public String doUppercaseActionWithBarException(String arg);

}
