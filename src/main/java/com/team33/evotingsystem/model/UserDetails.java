package com.team33.evotingsystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int userId;

    private int voterId;
    private String firstName;
    private String lastName;
    private Date dob;
    private int age;
    private String gender;
    private String religion;
    private String address;
    private String phoneNo;
    private String constituency;
    private String district;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login_credentials", referencedColumnName = "user_id")
    private LoginCredentials loginCredentials;
}
