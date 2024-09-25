package com.healthplan.work.service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeMapper challengeMapper;

    public List<ChallengeEntity> selectChallengeList() throws Exception {
        return challengeMapper.selectChallengeList();
    }

    public ChallengeEntity selectChallengeRead(int bno) throws Exception {
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