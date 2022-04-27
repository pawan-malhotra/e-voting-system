package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    Optional<UserDetails> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("update UserDetails set voterId=:voterId where userId=:userId")
    int addVoterIdByUserId(String voterId, String userId);

    @Query("select voterId from UserDetails where userId=:userId")
    Optional<String> findVoterIdByUserId(String userId);
}
