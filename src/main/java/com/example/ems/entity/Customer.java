package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name= "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_no")
    private String mobile;

    private String address;

    @CreatedDate
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_rented_date")
    private Date rentedDate;
    @CreatedDate
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_return_date")
    private Date returnDate;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Items> items;
}
