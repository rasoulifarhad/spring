package com.example.farhad.utilsdemo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PojoClass implements Serializable{
    
    static final long serialVersionUID = 1L;

    private int id ;
    private String text;
}
