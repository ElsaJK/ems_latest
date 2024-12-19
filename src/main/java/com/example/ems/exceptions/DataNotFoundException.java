package com.example.ems.exceptions;

import lombok.Getter;

@Getter

public class DataNotFoundException extends APException {
    private String name;

    public DataNotFoundException(String message) {
        super(message);
    }


    public DataNotFoundException(String code, String message) {
        super(code, message);

    }
}

	
    

    