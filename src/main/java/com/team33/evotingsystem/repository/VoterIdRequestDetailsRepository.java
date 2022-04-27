package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.UserDetails;
import com.team33.evotingsystem.model.VoterIdRequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoterIdRequestDetailsRepository extends JpaRepository<VoterIdRequestDetails,Integer> {
    @Query("from VoterIdRequestDetails where status=:status")
    List<VoterIdRequestDetails> findAllByStatus(String status);

    @Modifying
    @Transactional
    @Query("delete VoterIdRequestDetails where userId=:userId")
    void deleteByUserId(String userId);

    @Query("from VoterIdRequestDetails where userId=:userId")
    Optional<VoterIdRequestDetails> findByUserId(String userId);
}
