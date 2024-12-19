package com.example.ems.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String id;
    private String name;
    private String email;
    private String contactNo;
    private String addresses;
    private String userName;
    private String password;
    private RoleDTO role;
}
