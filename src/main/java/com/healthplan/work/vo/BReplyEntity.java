package com.healthplan.work.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BReplyEntity {
	private int rno;         // 댓글 번호
	private int bno;         // 게시글 번호
	private int mno;         // 회원 번호(작성자)
	private String rcomment; // 댓글 내용
	private Date rregdate;   // 댓글 작성일
}
