package com.team33.evotingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class VoterIdRequestDto {
    private List<String> userId;
}
