package com.example.ems.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterRequestDTO {
    private String name;
    private String email;
    private String userName;
    private String roleName;
    private String roleCode;
}
