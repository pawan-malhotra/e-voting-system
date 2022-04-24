package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    Optional<UserDetails> findByUserId(String userId);
}
