package com.healthplan.work.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ExerciseEntity {

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
