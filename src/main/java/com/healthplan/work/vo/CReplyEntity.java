package com.healthplan.work.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CReplyEntity {

    private Integer rno;
    private Integer mno;
    private Integer cno;
    private String rcomment;
    private Date rregdate;
    private String uuid;

}
