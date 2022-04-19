package com.team33.evotingsystem.controller;

import com.team33.evotingsystem.dto.ForgotPasswordDTO;
import com.team33.evotingsystem.dto.RegisterDTO;
import com.team33.evotingsystem.service.LoginRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class LoginRegisterController {

    @Autowired
    LoginRegistrationService loginRegistrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        model.addAttribute("message","");
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") RegisterDTO registerDTO) {
        // checking for null
        if (registerDTO == null) {
            return "redirect:/register?error=true&message="+"please try after some time!";
        }
        // userId validation
        try {
            if (registerDTO.getUserId().trim().length() != 12) {
                return "redirect:/register?error=true&message="+"please enter valid aadhar number!";
            }

            Double.parseDouble(registerDTO.getUserId().trim());
        } catch (NumberFormatException n) {
            return "redirect:/register?error=true&message="+"please enter valid aadhar number!";
        }
        if(loginRegistrationService.findByUserId(registerDTO.getUserId().trim()).isPresent()) {
            return "redirect:/register?error=true&message="+"user already registered!";
        }
        // password validation
        if (registerDTO.getPassword().trim().isEmpty() || registerDTO.getPassword().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"password should not empty!";
        }
        if(!registerDTO.getPassword().trim().equals(registerDTO.getConfirmPassword().trim())) {
            return "redirect:/register?error=true&message="+"password miss-matched!";
        }
        // first name validation
        if(registerDTO.getFirstName().trim().isEmpty() || registerDTO.getFirstName().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"first name should not be empty!";
        }
        // age validation
        if(registerDTO.getDob() == null) {
            return "redirect:/register?error=true&message="+"dob should not be empty!";
        }
        LocalDate dob = registerDTO.getDob().toLocalDate();
        long year = ChronoUnit.YEARS.between(dob,LocalDate.now());
        if(year < 18) {
            return "redirect:/register?error=true&message="+"Age should be minimum 18 years!";
        }
        // gender validation
        if(registerDTO.getGender() == null || registerDTO.getGender().trim().isEmpty() || registerDTO.getGender().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"gender should not be empty!";
        }
        // religion validation
        if(registerDTO.getReligion() == null || registerDTO.getReligion().trim().isEmpty() || registerDTO.getReligion().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"please select your religion!";
        }
        // caste validation
        if(registerDTO.getCaste() == null || registerDTO.getCaste().trim().isEmpty() || registerDTO.getCaste().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"please select your caste!";
        }
        // address validation
        if(registerDTO.getAddress().trim().isEmpty() || registerDTO.getAddress().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"address should not be empty!";
        }
        // state validation
        if(registerDTO.getState() == null || registerDTO.getState().trim().isEmpty() || registerDTO.getState().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"please select your state!";
        }
        // city validation
        if(registerDTO.getCity() == null || registerDTO.getCity().trim().isEmpty() || registerDTO.getCity().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"please select your city!";
        }
        // phone no validation
        if(registerDTO.getPhoneNo().trim().isEmpty() || registerDTO.getPhoneNo().trim().isBlank()) {
            return "redirect:/register?error=true&message="+"phone number should not empty!";
        }
        try {
            if (registerDTO.getPhoneNo().trim().length() != 10) {
                return "redirect:/register?error=true&message="+"phone number should contain minimum 10 numbers!";
            }

            Double.parseDouble(registerDTO.getPhoneNo().trim());
        } catch (NumberFormatException n) {
            return "redirect:/register?error=true&message="+"please enter valid phone number!";
        }

        if(loginRegistrationService.saveUserDetails(registerDTO))
            return "redirect:/login";
        else
            return "redirect:/register?error=true&message="+"please try after some time!";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgotPasswordDto", new ForgotPasswordDTO());
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String resetPassword(@ModelAttribute("forgotPasswordDto") ForgotPasswordDTO passwordDto) {
        // checking for null
        if (passwordDto == null) {
            return "redirect:/forgot-password?error=true&message="+"please try after some time!";
        }
        // userId validation
        try {
            if (passwordDto.getUserId().trim().length() != 12) {
                return "redirect:/forgot-password?error=true&message="+"please enter valid aadhar number!";
            }

            Double.parseDouble(passwordDto.getUserId().trim());
        } catch (NumberFormatException n) {
            return "redirect:/forgot-password?error=true&message="+"please enter valid aadhar number!";
        }
        if(loginRegistrationService.findByUserId(passwordDto.getUserId().trim()).isEmpty()) {
            return "redirect:/forgot-password?error=true&message="+"user not registered!";
        }
        // password validation
        if (passwordDto.getPassword().trim().isEmpty() || passwordDto.getPassword().trim().isBlank()) {
            return "redirect:/forgot-password?error=true&message="+"password should not empty!";
        }
        if(!passwordDto.getPassword().trim().equals(passwordDto.getConfirmPassword().trim())) {
            return "redirect:/forgot-password?error=true&message="+"password miss-matched!";
        }

        if(loginRegistrationService.resetPassword(passwordDto))
            return "redirect:/login";
        else
            return "redirect:/forgot-password?error=true&message="+"please try after some time!";
    }
}
