package com.healthplan.work.dao;

import com.healthplan.work.vo.SReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SReplyMapper {

    public void addReply(SReplyVO vo) throws Exception;

    public List<SReplyVO> listReply(int sno) throws Exception;

    public void updateReply(SReplyVO vo) throws Exception;

    public int getSno(int rno) throws Exception;

    public void deleteReply(int rno) throws Exception;
}