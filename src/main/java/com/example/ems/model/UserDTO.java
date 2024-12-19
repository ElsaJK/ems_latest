package com.example.ems.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String name;
    private String addresses;
    private String userName;
    private RoleDTO role;
    private String token;
}

