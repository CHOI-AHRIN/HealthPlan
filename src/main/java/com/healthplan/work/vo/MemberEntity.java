package com.healthplan.work.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
public class MemberEntity {
    private Integer mno;
    private String uuid;
    private String phone;
    private String upw;
    private String name;
    private String mtype;
    private String email;
    private Date regdate;
    private String sstype;
    private Integer pcount;

    private String sessionkey;
    private Date sessionlimit;

}
