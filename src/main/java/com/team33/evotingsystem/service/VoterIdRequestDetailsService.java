package com.team33.evotingsystem.service;

import com.team33.evotingsystem.model.UserDetails;
import com.team33.evotingsystem.model.VoterIdRequestDetails;
import com.team33.evotingsystem.repository.UserDetailsRepository;
import com.team33.evotingsystem.repository.VoterIdRequestDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VoterIdRequestDetailsService {
    @Autowired
    VoterIdRequestDetailsRepository voterIdRequestDetailsRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;

    // retrieve all voterId requests
    public List<VoterIdRequestDetails> findAllVoterIdRequest(String status) {
        return voterIdRequestDetailsRepository.findAllByStatus(status);
    }

    // generate new voterId
    public String generateNewVoterId() {
        return System.currentTimeMillis() + "" + LocalDate.now().getYear() + LocalDate.now().getMonth()
                + new Random(1000).nextInt();
    }

    // user details who requested for voter id
    public List<UserDetails> findAllUserDetails() {
        List<VoterIdRequestDetails> vDetails = voterIdRequestDetailsRepository.findAll();
        List<UserDetails> filteredList = new ArrayList<>();
        for(VoterIdRequestDetails v:vDetails) {
            filteredList.add(userDetailsRepository.findByUserId(v.getUserId()).get());
        }
        return filteredList;
    }

    public void deleteByUserId(String userId) {
        voterIdRequestDetailsRepository.deleteByUserId(userId);
    }

    public Optional<VoterIdRequestDetails> findByUserId(String userId) {
        return voterIdRequestDetailsRepository.findByUserId(userId);
    }

    public boolean save(VoterIdRequestDetails details) {
        return voterIdRequestDetailsRepository.save(details).getId() > 0;
    }
}
