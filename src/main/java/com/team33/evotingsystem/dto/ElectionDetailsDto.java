package com.team33.evotingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectionDetailsDto {
    private String electionName;
    private String electionDate;
    private String votingTime;
}
