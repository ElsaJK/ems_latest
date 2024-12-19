package com.example.ems.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {

    @Email(message = "Please enter a valid email ID")
    private String email;

    @Pattern(regexp = "^$|^[6-9]\\d{9}$", message = "Please enter a valid mobile number")
    private String mobile;
}