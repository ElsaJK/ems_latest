package com.example.ems.exceptions;

import lombok.Getter;

@Getter
public class DuplicateDataException extends APException {

    private String name;

    public DuplicateDataException(String code, String message) {
        super(code, message);
    }

    public DuplicateDataException(String message) {
        super(message);
    }
}
