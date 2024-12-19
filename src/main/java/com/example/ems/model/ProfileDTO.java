package com.example.ems.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDTO {

    private String name;

    private VerificationResponseDTO email;

    private VerificationResponseDTO mobile;

}