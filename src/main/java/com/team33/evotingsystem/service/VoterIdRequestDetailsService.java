package com.team33.evotingsystem.service;

import com.team33.evotingsystem.model.VoterIdRequestDetails;
import com.team33.evotingsystem.repository.VoterIdRequestDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class VoterIdRequestDetailsService {
    @Autowired
    VoterIdRequestDetailsRepository voterIdRequestDetailsRepository;

    // retrieve all voterId requests
    public List<VoterIdRequestDetails> findAllVoterIdRequest(String status) {
        return voterIdRequestDetailsRepository.findAllByStatus(status);
    }

    // generate new voterId
    public String generateNewVoterId() {
        return System.currentTimeMillis() + "" + LocalDate.now().getYear() + LocalDate.now().getMonth()
                + new Random(1000).nextInt();
    }
}
