package com.team33.evotingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String userId;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String religion;
    private String caste;
    private String address;
    private String state;
    private String city;
    private String phoneNo;

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", religion='" + religion + '\'' +
                ", caste='" + caste + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
