package com.team33.evotingsystem.controller;

import com.team33.evotingsystem.dto.ElectionDetailsDto;
import com.team33.evotingsystem.model.CustomUserDetails;
import com.team33.evotingsystem.model.ElectionDetails;
import com.team33.evotingsystem.model.UserDetails;
import com.team33.evotingsystem.model.VoterIdRequestDetails;
import com.team33.evotingsystem.service.LoginRegistrationService;
import com.team33.evotingsystem.service.VoterIdRequestDetailsService;
import com.team33.evotingsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VoterController {

    @Autowired
    VoterIdRequestDetailsService voterIdRequestDetailsService;
    @Autowired
    LoginRegistrationService loginRegistrationService;
    @Autowired
    VoterService voterService;

    @GetMapping("/voter")
    public String voterPage() {
        return "voter-home";
    }

    @GetMapping("/voter/request-for-voter-id")
    public String requestForVoterId(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserDetails) {
            String userId = ((CustomUserDetails)principal).getUsername();
            Optional<VoterIdRequestDetails> voterIdRequest = voterIdRequestDetailsService.findByUserId(userId);
            if(voterIdRequest.isPresent()) {
                model.addAttribute("status",voterIdRequest.get().getStatus());
            } else {
                Optional<String> voterId = loginRegistrationService.findVoterIdByUserId(userId);
                if(voterId.isPresent()) {
                    model.addAttribute("status","Already Generated");
                } else {
                    model.addAttribute("status", "Not Yet Requested");
                }
            }
        }
        return "voter-home";
    }

    @GetMapping("/voter/send-request")
    public String processRequestForVoterId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserDetails) {
            String userId = ((CustomUserDetails)principal).getUsername();
            boolean result = voterIdRequestDetailsService.save(new VoterIdRequestDetails(userId,"pending"));
        }
        return "redirect:/voter/request-for-voter-id";
    }

    @GetMapping("/voter/view-voter-id")
    public String viewVoterId(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserDetails) {
            String userId = ((CustomUserDetails)principal).getUsername();
            Optional<VoterIdRequestDetails> voterIdRequest = voterIdRequestDetailsService.findByUserId(userId);
            if(voterIdRequest.isPresent()) {
                model.addAttribute("voterId",voterIdRequest.get().getStatus());
            } else {
                Optional<String> voterId = loginRegistrationService.findVoterIdByUserId(userId);
                if(voterId.isPresent()) {
                    model.addAttribute("voterId",voterId.get());
                } else {
                    model.addAttribute("voterId", "Not Yet Requested");
                }
            }
        }
        return "voter-home";
    }

    @GetMapping("/voter/view-election-details")
    public String viewElectionDetails(Model model) {
        List<ElectionDetails> eDetailsList = voterService.recentElectionDetails();
        List<ElectionDetailsDto> detailsDtoList = new ArrayList<>();
        eDetailsList.forEach(eDetails -> {
                ElectionDetailsDto detailsDto = new ElectionDetailsDto();
                detailsDto.setElectionName(eDetails.getElectionName());
                detailsDto.setVotingTime(eDetails.getVotingTime());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                String date = sdf.format(eDetails.getElectionDate());
                detailsDto.setElectionDate(date);
                detailsDtoList.add(detailsDto);
        });
        model.addAttribute("electionDetails",detailsDtoList);

        return "voter-home";
    }
}
