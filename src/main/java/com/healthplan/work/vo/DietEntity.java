package com.healthplan.work.vo;

import lombok.Data;
import java.util.Date;

@Data
public class DietEntity {

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
