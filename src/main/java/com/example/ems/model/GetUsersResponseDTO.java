package com.example.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponseDTO {

    private String id;

    private String name;

    private VerificationResponseDTO email;

    private VerificationResponseDTO mobile;


    private LocalDateTime createdDate;

}