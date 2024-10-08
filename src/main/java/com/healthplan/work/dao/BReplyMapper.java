package com.healthplan.work.dao;

import com.healthplan.work.vo.BReplyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BReplyMapper {

    // 댓글 목록 조회
    // 댓글 조회
    List<BReplyEntity> listReply(int bno) throws Exception;

    // 댓글 등록
    void addReply(BReplyEntity vo) throws Exception;

    // 댓글 수정
    void modifyReply(BReplyEntity vo) throws Exception;

    // 댓글 삭제
    void removeReply(int rno) throws Exception;
}
