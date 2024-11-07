package com.healthplan.work.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {

    private int pno;
    private int mno;
    private String psource;
    private int pcount;
    private Date pdate;


}
