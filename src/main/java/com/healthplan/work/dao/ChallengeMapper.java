package com.healthplan.work.dao;
import com.healthplan.work.vo.ChallengeEntity;
import java.util.List;

public interface ChallengeMapper {
        List<ChallengeEntity> selectChallengeList() throws Exception;
        void createChallengeReg(ChallengeEntity ch) throws Exception;
        // 수정해야 돼 흑흑

}