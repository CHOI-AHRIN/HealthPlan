package com.healthplan.work.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeEntity {
    // private int sno;

    private int bno;           // 게시글 번호
    private int mno;           // 회원 번호 (작성자)
    private String title;      // 챌린지 제목
    private String bcontents;  // 챌린지 내용
    private String wdate;      // 작성 날짜 (원래 Date 형식으로, java.util.Date 임포트했었음)
    private int bcounts;       // 조회수

    // [아린] uuid 조회를 위한 추가
    private String uuid;

    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
