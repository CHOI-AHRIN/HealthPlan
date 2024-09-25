package com.healthplan.work.service;

import com.healthplan.work.dao.BReplyMapper;
import com.healthplan.work.vo.BReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BReplyService {

    @Autowired
    private BReplyMapper bReplyMapper;

    public List<Map<String, Object>> getRepliesByBno(int bno) throws Exception {
        return bReplyMapper.getRepliesWithMemberInfo(bno);
    }

    // 새로운 댓글 추가
    @Transactional
    public void insertReply(BReplyEntity vo) throws Exception {
        bReplyMapper.insertReply(vo);
    }

    // 댓글 수정
    @Transactional
    public void updateReply(BReplyEntity vo) throws Exception {
        bReplyMapper.updateReply(vo);
    }

    // 댓글 삭제
    @Transactional
    public void deleteReply(int rno) throws Exception {
        bReplyMapper.deleteReply(rno);
    }

    // 댓글 상세 조회
    public BReplyEntity getReply(int rno) throws Exception {
        return bReplyMapper.getReply(rno);
    }
}
