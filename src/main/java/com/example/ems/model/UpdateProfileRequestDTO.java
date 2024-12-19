package com.example.ems.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateProfileRequestDTO {

    @NotNull
    @NotBlank(message = "Minimum one character required")
    private String name;

    @NotNull
    @NotBlank(message = "Please enter a valid email ID")
    @Email(message = "Please enter a valid email ID")
    private String email;

    @NotNull
    @NotBlank(message = "Please enter mobile number")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter a valid mobile number")
    private String mobile;

}
