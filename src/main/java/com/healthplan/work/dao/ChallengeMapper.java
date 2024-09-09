package com.healthplan.work.dao;
import com.healthplan.work.vo.ChallengeEntity;
import java.util.List;

public interface ChallengeMapper {
        List<ChallengeEntity> selectChallengeList() throws Exception;
        void createChallengeReg(ChallengeEntity ch) throws Exception;
       /* List<> selectComments(int bno) throws Exception; // 댓글 조회 메서드 추가*/
        List<ChallengeEntity> selectRecentChallenges(int limit) throws Exception; // 최근 챌린지 조회
}