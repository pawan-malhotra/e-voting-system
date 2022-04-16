package com.team33.evotingsystem.service;

import com.team33.evotingsystem.model.CustomUserDetails;
import com.team33.evotingsystem.model.LoginCredentials;
import com.team33.evotingsystem.repository.LoginCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<LoginCredentials> optionalUser = loginCredentialsRepository.findByUserId(Integer.parseInt(userId));
        optionalUser.orElseThrow(()-> new UsernameNotFoundException("User Not Found!"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
