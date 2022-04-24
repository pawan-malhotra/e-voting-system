package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.VoterIdRequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterIdRequestDetailsRepository extends JpaRepository<VoterIdRequestDetails,Integer> {
    @Query("from VoterIdRequestDetails where status=:status")
    List<VoterIdRequestDetails> findAllByStatus(String status);
}
