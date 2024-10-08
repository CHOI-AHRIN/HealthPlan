package com.healthplan.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data




public class DietDTO {

    private Integer cno;
    private String mno;
    private String ctype;
    private String title;
    private String contents;
    private Date bdate;
    private Integer counts;
    private Integer replycnt;

    private String uuid;

    private String[] files;

}
