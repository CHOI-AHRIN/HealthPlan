package com.healthplan.work.service;

import com.healthplan.work.vo.BReplyEntity;
/*import com.healthplan.domain.Criteria;*/
import com.healthplan.work.vo.BReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BReplyService {

    @Autowired
    private BreplyMapper breplyMapper;

    // 새로운 댓글 추가
    void addReply(BReplyEntity vo) throws Exception;
    void addReply(BReplyEntity vo, String fileName) throws Exception;
    List<BReplyEntity> listRepliesWithAttachments(Integer bno) throws Exception;

    // 기존 댓글 수정
    void modifyReply(BReplyEntity vo) throws Exception;

    // 댓글 ID로 댓글 삭제
    void removeReply(int rno) throws Exception;

    // 모든 댓글 목록 가져오기 (게시글 번호 없이)
    /*List<BReplyEntity> listReplies() throws Exception;*/

    public List<BReplyEntity> listReplies() {

        return Breplymapper.
    }

/*    // 페이징된 댓글 목록 가져오기
    List<BReplyEntity> listRepliesPage(Criteria cri) throws Exception;*/

/*    // 전체 댓글 수 조회
    int countTotalReplies() throws Exception;*/
    
/*    // 가장 최근 n개의 댓글 가져오기
    List<BReplyEntity> listRecentReplies(int limit) throws Exception;*/
}
