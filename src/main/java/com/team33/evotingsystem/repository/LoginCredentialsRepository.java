package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials,Integer> {

    Optional<LoginCredentials> findByUserId(String userId);
}
