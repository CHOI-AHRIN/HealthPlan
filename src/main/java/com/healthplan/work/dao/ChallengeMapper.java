package com.healthplan.work.dao;

import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChallengeMapper {
        List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception;
        public int selectChallengeCount(SearchCriteria cri) throws Exception;

        public ChallengeEntity selectChallengeRead(int bno) throws Exception;

        void insertChallenge(ChallengeEntity vo) throws Exception;
        void updateChallenge(ChallengeEntity vo) throws Exception;
        void deleteChallenge(int bno) throws Exception;
}
