package com.example.ems.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private Boolean success = true;

    private String message = "";

    private T result;


    public ResponseDTO(T t) {
        this.setResult(t);
    }

    public ResponseDTO(String message, T result) {
        this.message = message;
        this.result = result;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }
}