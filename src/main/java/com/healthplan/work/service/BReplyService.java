package com.healthplan.work.service;


import com.healthplan.work.dao.BReplyMapper;
import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.BReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BReplyService {

    @Autowired
    private BReplyMapper breplyMapper;

    @Autowired
    private ChallengeMapper mapper;

    // 댓글 목록 조회
    public List<BReplyEntity> listReply(int bno) throws Exception {
        return breplyMapper.listReply(bno);
    }

    // 댓글 등록
    public void addReply(BReplyEntity vo) throws Exception {
        breplyMapper.addReply(vo);
        mapper.updateReplyCnt2(vo);
    }

    // 댓글 수정
    public void modifyReply(BReplyEntity vo) throws Exception {
        breplyMapper.modifyReply(vo);
    }

    // 댓글 삭제
    public void removeReply(int rno) throws Exception {
        breplyMapper.removeReply(rno);
    }
}