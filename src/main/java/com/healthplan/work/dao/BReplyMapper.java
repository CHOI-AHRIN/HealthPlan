package com.healthplan.work.dao;

import com.healthplan.work.vo.BReplyEntity;

import java.util.List;
import java.util.Map;

public interface BReplyMapper {

    // 댓글 추가
    void addReply(BReplyEntity vo) throws Exception;

    // 댓글 수정
    void update(BReplyEntity vo) throws Exception;

    // 댓글 삭제
    void delete(int rno) throws Exception;

    // 모든 댓글 목록 가져오기
    List<BReplyEntity> listAll() throws Exception;

    // 전체 댓글 수 조회
    int countTotalReplies() throws Exception;

    // 특정 게시글의 댓글 목록 가져오기
    List<BReplyEntity> selectRepliesWithAttachments(Integer bno) throws Exception;

    // 첨부 파일 삽입
    void insertAttachedFile(Map<String, Object> params) throws Exception;

    // 가장 최근 n개의 댓글 가져오기
    List<BReplyEntity> listRecentReplies(Map<String, Object> params) throws Exception;
    
 	// 탈퇴한 자의 댓글 삭제
 	public void ndelete(String uuid) throws Exception;
}
