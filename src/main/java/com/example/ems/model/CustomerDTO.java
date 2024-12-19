package com.example.ems.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private Date rentedDate;
    private Date returnDate;

    public CustomerDTO(String id, String name, String addresses, String mobile, String email, Date rentedDate, Date returnDate) {
    }


}
