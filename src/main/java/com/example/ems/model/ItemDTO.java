package com.example.ems.model;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String id;

    private String name;

    private String image;
    private String action;
    private Integer count;

    private Integer totalStock;
    private Integer outStock;
    private Integer balance;
    private Date itemDisbursedDate;
    private Date itemReturnedDate;
    private String customerName;
    private String customerMobile;

    public ItemDTO(String id, String name, String image, int totalStock, int outStock, int balance) {
    }
}
