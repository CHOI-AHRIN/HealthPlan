package com.healthplan.work.service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeMapper challengeMapper;

    public List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeList(cri);
    }

    public int selectChallengeCount(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeCount(cri);
    }

    public ChallengeEntity selectChallengeRead(int bno) throws Exception {
        challengeMapper.updateChallengeCount(bno);
        return challengeMapper.selectChallengeRead(bno);
    }

    public void challengeInsert(ChallengeEntity vo) throws Exception {
        challengeMapper.insertChallenge(vo);
    }

    public void challengeUpdate(ChallengeEntity vo) throws Exception {
        challengeMapper.updateChallenge(vo);
    }

    public void challengeDelete(int bno) throws Exception {
        challengeMapper.deleteChallenge(bno);
    }

}
