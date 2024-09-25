package com.healthplan.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ChallengeDTO {
    private Long id;
    private String name;
    private String description;
    private int difficulty;
}
