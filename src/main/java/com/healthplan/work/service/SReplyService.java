package com.healthplan.work.service;

import com.healthplan.work.dao.SReplyMapper;
import com.healthplan.work.dao.SubscribeMapper;
import com.healthplan.work.vo.SReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SReplyService {

    @Autowired
    private SReplyMapper sreplyMapper;

    @Autowired
    private SubscribeMapper subscribeMapper;

    public void addReply(SReplyVO vo) throws Exception {
        sreplyMapper.addReply(vo);
        subscribeMapper.updateReplyCnt(vo);
    }

    public List<SReplyVO> listReply(int sno) throws Exception {
        return sreplyMapper.listReply(sno);
    }

    public void modifyReply(SReplyVO vo) throws Exception {
        sreplyMapper.updateReply(vo);
    }

    public void removeReply(int rno) throws Exception {
        int sno = sreplyMapper.getSno(rno);
        sreplyMapper.deleteReply(rno);
        subscribeMapper.updateReplyCntSubtraction(sno);
    }
}