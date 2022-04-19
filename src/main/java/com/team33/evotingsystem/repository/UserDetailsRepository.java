package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    Optional<UserDetails> findByUserId(String userId);
}
