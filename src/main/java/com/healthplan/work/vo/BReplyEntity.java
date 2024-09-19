package com.healthplan.work.vo;

import java.util.Date;
import java.util.List;


public class BReplyEntity {
	private int rno;        // 댓글 번호
	private int bno;        // 게시글 번호
	private int chno;       // 챌린지 번호
	private int mno;        // 작성자 번호
	private String rComment; // 댓글 내용
	private int aNo;        // 첨부 파일 번호
	private String fileName; // 파일 이름
	private Date rRegdate;  // 댓글 작성일
	private String status;  // 댓글 상태

	// 댓글 목록 추가
	private List<BReplyEntity> comments; // 댓글 리스트

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getChno() {
		return chno;
	}

	public void setChno(int chno) {
		this.chno = chno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getrComment() {
		return rComment;
	}

	public void setrComment(String rComment) {
		this.rComment = rComment;
	}

	public int getaNo() {
		return aNo;
	}

	public void setaNo(int aNo) {
		this.aNo = aNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getrRegdate() {
		return rRegdate;
	}

	public void setrRegdate(Date rRegdate) {
		this.rRegdate = rRegdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BReplyEntity> getComments() {
		return comments;
	}

	public void setComments(List<BReplyEntity> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BReplyVO [rno=" + rno + ", bno=" + bno + ", chno=" + chno + ", mno=" + mno + ", rComment=" + rComment
				+ ", aNo=" + aNo + ", fileName=" + fileName + ", rRegdate=" + rRegdate + ", status=" + status
				+ ", comments=" + comments + "]";
	}



}
