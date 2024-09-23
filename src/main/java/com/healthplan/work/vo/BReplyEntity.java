package com.healthplan.work.vo;

import java.util.Date;


public class BReplyEntity {
	private int rno;        // 댓글 번호
	private int bno;        // 게시글 번호
	private int mno;        // 회원 번호(작성자)
	private String rComment; // 댓글 내용
	private Date rRegdate;  // 댓글 작성일

	public void setRno(int rno) {
		this.rno = rno;
	}

}
