package com.team33.evotingsystem.repository;

import com.team33.evotingsystem.model.ElectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionDetailsRepository extends JpaRepository<ElectionDetails,Integer> {
    @Query("from ElectionDetails ORDER BY electionDate DESC")
    List<ElectionDetails> recentElectionDetails();
}
