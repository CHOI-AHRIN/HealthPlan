package com.healthplan.work.service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    void createChallengeReg(ChallengeEntity ch) throws Exception;
    List<ChallengeEntity> selectChallengeList() throws Exception;
    List<ChallengeEntity> selectChallengeList(ChallengeEntity ch) throws Exception;
    List<ChallengeEntity> selectComments(int bno) throws Exception; // 댓글 조회 메서드 추가
    List<ChallengeEntity> selectRecentChallenges(int limit) throws Exception; // 최근 챌린지 조회 메서드 추가

}
