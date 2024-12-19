package com.example.ems.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserFilterResponse {
    private Long count;
    private Integer pageCount;
    private List<UserRequestDTO> userList;
}
