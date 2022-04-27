package com.team33.evotingsystem.service;

import com.team33.evotingsystem.dto.ForgotPasswordDTO;
import com.team33.evotingsystem.dto.RegisterDTO;
import com.team33.evotingsystem.model.LoginCredentials;
import com.team33.evotingsystem.model.Role;
import com.team33.evotingsystem.model.UserDetails;
import com.team33.evotingsystem.repository.LoginCredentialsRepository;
import com.team33.evotingsystem.repository.RoleRepository;
import com.team33.evotingsystem.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginRegistrationService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    public boolean saveUserDetails(RegisterDTO dto) {
        UserDetails user = new UserDetails();
        user.setUserId(dto.getUserId().trim());
        user.setFirstName(dto.getFirstName().trim());
        user.setLastName(dto.getLastName().trim());
        user.setGender(dto.getGender().trim());
        user.setCaste(dto.getCaste().trim());
        user.setReligion(dto.getReligion().trim());
        user.setDob(dto.getDob());
        user.setAddress(dto.getAddress().trim());
        user.setState(dto.getState().trim());
        user.setCity(dto.getCity().trim());
        user.setPhoneNo(dto.getPhoneNo().trim());

        LoginCredentials credentials = new LoginCredentials();
        credentials.setUserId(dto.getUserId().trim());
        credentials.setPassword(bCryptPasswordEncoder.encode(dto.getPassword().trim()));
        credentials.setRoles(new ArrayList<>(List.of(roleRepository.findById(2).orElse(new Role(4,"ROLE_USER")))));

        user.setLoginCredentials(credentials);

        UserDetails saved = userDetailsRepository.save(user);
        return saved.getId() > 0;
    }

    public Optional<UserDetails> findByUserId(String userId) {
        return userDetailsRepository.findByUserId(userId);
    }

    public boolean resetPassword(ForgotPasswordDTO dto) {
        int updatedCredential = loginCredentialsRepository.updatePassword(
                bCryptPasswordEncoder.encode(dto.getPassword().trim()),
                dto.getUserId().trim()
        );

        return updatedCredential>0;
    }

    public boolean addVoterIdByUserId(String voterId, String userId) {
        return userDetailsRepository.addVoterIdByUserId(voterId,userId) > 0;
    }

    public Optional<String> findVoterIdByUserId(String userId) {
        return userDetailsRepository.findVoterIdByUserId(userId);
    }
}
