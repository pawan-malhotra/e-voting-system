package com.team33.evotingsystem.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends LoginCredentials implements UserDetails {

    public CustomUserDetails(LoginCredentials credentials) {
        super(credentials);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        super.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return String.valueOf(super.getUserId());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
