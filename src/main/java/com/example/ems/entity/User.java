package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;
    private String userName;
    private String password;

    @Column(unique = true)
    private String email;
    @Column(name = "profile_name")
    private String name;
    @Column(unique = true)
    private String mobile;
//    @ElementCollection(targetClass = UserType.class,fetch = FetchType.LAZY)
//    @Enumerated(EnumType.ORDINAL)
//    private Set<UserType>userTypes= new HashSet<>();
    private Status status = Status.Active;
    @Column(unique = true)
    private String login;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
