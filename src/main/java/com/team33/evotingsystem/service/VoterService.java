package com.team33.evotingsystem.service;

import com.team33.evotingsystem.model.ElectionDetails;
import com.team33.evotingsystem.repository.ElectionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    ElectionDetailsRepository electionDetailsRepository;

    public boolean save(ElectionDetails details) {
        return electionDetailsRepository.save(details).getId() > 0;
    }

    public List<ElectionDetails> recentElectionDetails() {
        return electionDetailsRepository.recentElectionDetails();
    }
}
