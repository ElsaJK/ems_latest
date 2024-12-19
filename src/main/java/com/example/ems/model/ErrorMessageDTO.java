package com.example.ems.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String message;
    private String detail;
}
