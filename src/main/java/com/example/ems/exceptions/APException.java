package com.example.ems.exceptions;

import lombok.Getter;

@Getter
public class APException extends RuntimeException {

    String code;
    String message;

    public String getCode() {
        return code;
    }

    public APException(String code, String message) {
        super(code + " : " + message);
        this.code = code;
        this.message = message;
    }

    public APException(String message) {
        super(message);
    }

}