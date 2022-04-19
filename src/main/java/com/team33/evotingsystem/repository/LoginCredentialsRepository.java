package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials,Integer> {

    Optional<LoginCredentials> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("update LoginCredentials set password=:password where userId=:userId")
    int updatePassword(String password, String userId);
}
