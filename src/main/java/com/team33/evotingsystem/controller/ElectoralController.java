package com.team33.evotingsystem.controller;

import com.team33.evotingsystem.dto.VoterIdRequestDto;
import com.team33.evotingsystem.service.LoginRegistrationService;
import com.team33.evotingsystem.service.VoterIdRequestDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ElectoralController {

    @Autowired
    VoterIdRequestDetailsService voterIdRequestDetailsService;
    @Autowired
    LoginRegistrationService loginRegistrationService;

    @GetMapping("/electoral")
    public String electoralPage() {
        return "electoral-home";
    }

    @GetMapping("/electoral/view-voter-id-requests")
    public String viewVoterIdRequests(Model model) {
        model.addAttribute("userDetails",voterIdRequestDetailsService.findAllUserDetails());
        model.addAttribute("show",true);
        model.addAttribute("voterIdRequestDto", new VoterIdRequestDto());
        return "electoral-home";
    }

    @PostMapping("/electoral/generate-voter-id")
    public String generateVoterId(@ModelAttribute("voterIdRequestDto") VoterIdRequestDto voterIdRequests, Model model) {
        voterIdRequests.getUserId().forEach(userid -> {
            String newVoterId = voterIdRequestDetailsService.generateNewVoterId();
            boolean result = loginRegistrationService.addVoterIdByUserId(newVoterId,userid);
            if(result) {
                voterIdRequestDetailsService.deleteByUserId(userid);
            }
        });

        model.addAttribute("userDetails",voterIdRequestDetailsService.findAllUserDetails());
        model.addAttribute("show",true);
        model.addAttribute("voterIdRequestDto", new VoterIdRequestDto());
        return "redirect:/electoral/view-voter-id-requests";
    }
}
