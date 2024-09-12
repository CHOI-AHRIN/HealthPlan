package com.healthplan.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data


public class ChallengeDTO {

    private Long cno;
    private String title;
    private String contents;
    private String uuid;
    private Long mno;
    private LocalDateTime bdate;

}
