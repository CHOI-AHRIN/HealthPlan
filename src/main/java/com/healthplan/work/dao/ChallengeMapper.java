package com.healthplan.work.dao;

import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.MemberEntity;
import com.healthplan.work.vo.PointDTO;
import com.healthplan.work.vo.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChallengeMapper {
    // 챌린지 게시판
    List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception;

    public int selectChallengeCount(SearchCriteria cri) throws Exception;

    public ChallengeEntity selectChallengeRead(int bno) throws Exception;

    void insertChallenge(ChallengeEntity vo) throws Exception;

    void updateChallenge(ChallengeEntity vo) throws Exception;

    void deleteChallenge(int bno) throws Exception;

    public void updateChallengeCount(int bno) throws Exception;

    //챌린지 파일첨부
    public void challengeAttach(HashMap<String, Object> map) throws Exception;

    public void challengeStringAttach(String csno, String imgName) throws Exception;

    public List<ImageDTO> selectImageList(int sno) throws Exception;

    public void addAttach(String imgName, String imgURL, String uuid, String path, String imgType) throws Exception;

    public void deleteAttach(int sno) throws Exception;

    public void updateAttach(String imgName, String imgURL, String uuid, String path, String imgType, String sno) throws Exception;




    // ----------------------------------- 챌린지 공지 게시판 -----------------------------------

    // 공지 게시판 목록 조회
    List<ChallengeEntity> selectNoticeList(SearchCriteria cri) throws Exception;

    // 공지 게시판 글 개수 조회
    int selectNoticeCount(SearchCriteria cri) throws Exception;

    // 공지 게시글 상세 조회
    ChallengeEntity selectNoticeRead(int bno) throws Exception;

    // 공지 게시글 작성
    void insertNotice(ChallengeEntity vo) throws Exception;

    // 공지 게시글 수정
    void updateNotice(ChallengeEntity vo) throws Exception;

    // 공지 게시글 삭제
    void deleteNotice(int bno) throws Exception;

    // 공지 게시글 조회수 증가
    void updateNoticeCount(int bno) throws Exception;


    // 포인트 적립
    public void addPoint(PointDTO point) throws Exception;

    //랭킹
    public List<MemberEntity> cRank(MemberEntity mem) throws Exception;
}


   // 챌린지 공지 게시판 (수정필요)
    // List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception;

    // public int selectChallengeCount(SearchCriteria cri) throws Exception;

    // public ChallengeEntity selectChallengeRead(int bno) throws Exception;

    // void insertChallenge(ChallengeEntity vo) throws Exception;

    // void updateChallenge(ChallengeEntity vo) throws Exception;

    // void deleteChallenge(int bno) throws Exception;

    // public void updateChallengeCount(int bno) throws Exception;

