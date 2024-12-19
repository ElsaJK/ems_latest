package com.example.ems.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T response;

    private boolean success = true;

    private String errorCode;

    private String message;
}