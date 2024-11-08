package com.healthplan.work.dao;

import com.healthplan.work.vo.BReplyEntity;
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

    public List<ImageDTO> selectMainImage(int bno) throws Exception;

    public List<ImageDTO> selectImageList(int bno) throws Exception;

    public void addAttach(String imgName, String imgURL, String uuid, String path, String imgType) throws Exception;

    public void deleteAttach(int bno) throws Exception;

    public void updateAttach(String imgName, String imgURL, String uuid, String path, String imgType, String bno) throws Exception;

    // 포인트 적립
    public void addPoint(PointDTO point) throws Exception;

    // 랭킹
    public List<MemberEntity> cRank(MemberEntity mem) throws Exception;

    // 댓글수
    public void updateReplyCnt2(BReplyEntity vo) throws Exception;
}
