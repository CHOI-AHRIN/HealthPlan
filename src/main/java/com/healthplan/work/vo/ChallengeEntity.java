package com.healthplan.work.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class ChallengeEntity {

    private int bno; // 보드번호
    private int mno; // 회원번호
    private String bcontents; // 오늘의 챌린지 내용
    private Date wdate; // 작성된 날짜 및 시간 (필요?)
    private String btype; // 게시판 타입 (챌린지: 'H')
    private String chno; // 챌린지 번호 (①, ②, ③)
    private String dif; // 난이도 (별 1~5)
    private int bcounts; // 조회수
    private String title; // 챌린지 제목

}
