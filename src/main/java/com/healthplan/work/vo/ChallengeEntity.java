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

    private int bno;           // 게시글 번호
    private int mno;           // 회원 번호 (작성자)
    private String title;      // 챌린지 제목
    private String bcontents;  // 챌린지 내용
    private String wdate;      // 작성 날짜 (원래 Date 형식으로, java.util.Date 임포트했었음)
    private int bcounts;       // 조회수
    private String[] fileid;
    private String[] imgtype;
    private String titleimg;
    private String spoint;

    // [아린] uuid 조회를 위한 추가
    private String uuid;

    private int replycnt;


    @Builder.Default
    private List<ImageDTO> mainImage = new ArrayList<>();

    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
