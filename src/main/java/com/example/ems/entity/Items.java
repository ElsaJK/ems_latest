package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Items {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    private String name;
    private String image;

    @CreatedBy
    @Basic
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedBy
    @Basic
    @Column(name = "updated_by")
    private String updatedBy;

    @LastModifiedDate
    @Basic
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private int totalStock;
    private int outStock;
    private int balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
