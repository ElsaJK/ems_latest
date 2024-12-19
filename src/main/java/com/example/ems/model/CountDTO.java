package com.example.ems.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDTO {
    private String name;
    private Integer totalStock;
    private Integer outStock;
    private Integer balance;
}
