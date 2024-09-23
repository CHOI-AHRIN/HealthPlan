package com.healthplan.work.vo;

import lombok.Data;
import java.util.Date;

@Data
public class ChallengeEntity {
    private int bno;           // 게시글 번호
    private int mno;           // 회원 번호 (작성자)
    private String title;      // 챌린지 제목
    private String bcontents;  // 챌린지 내용
    private Date wdate;        // 작성 날짜
    private int bcounts;       // 조회수
}
