package com.team33.evotingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDTO {
    private String userId;
    private String password;
    private String confirmPassword;
}
