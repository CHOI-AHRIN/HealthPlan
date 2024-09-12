package com.healthplan.work.service;

import com.healthplan.work.vo.ChallengeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    public void createChallengeReg(ChallengeEntity ch) throws Exception {
    }

    public List<ChallengeEntity> selectChallengeList() throws Exception {
        return null;
    }

    public List<ChallengeEntity> selectChallengeList(ChallengeEntity ch) throws Exception {
        return null;
    }

    public List<ChallengeEntity> selectComments(int bno) throws Exception {
        return null;
    } // 댓글 조회 메서드 추가

    public List<ChallengeEntity> selectRecentChallenges(int limit) throws Exception {
        return null;
    } // 최근 챌린지 조회 메서드 추가
}
