package com.healthplan.work.dao;

import com.healthplan.work.vo.BReplyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BReplyMapper {

    // 모든 댓글 목록 조회
    List<BReplyEntity> getReplyList() throws Exception;

    // 특정 게시글의 댓글 목록 조회
    List<Map<String, Object>> getRepliesWithMemberInfo(int bno) throws Exception;

    // 댓글 등록
    void insertReply(BReplyEntity vo) throws Exception;

    // 댓글 수정
    void updateReply(BReplyEntity vo) throws Exception;

    // 댓글 삭제
    void deleteReply(int rno) throws Exception;

    // 댓글 상세 조회
    BReplyEntity getReply(int rno) throws Exception;
}

/*    @Select("SELECT r.*, m.nickname " +
            "FROM breply_table r JOIN member_table m ON r.mno = m.mno " +
            "WHERE r.bno = #{bno}")
    List<Map<String, Object>> getRepliesWithMemberInfo(@Param("bno") int bno) throws Exception;


    // 댓글 목록 조회
    @Select("SELECT * FROM breply_table ORDER BY rno DESC")
    List<BReplyEntity> getRepliesByBno() throws Exception;

    // 댓글 등록
    @Insert("INSERT INTO breply_table (bno, content, writer, regdate) VALUES (#{bno}, #{content}, #{writer}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "rno")
    void insertReply(BReplyEntity vo) throws Exception;

    // 댓글 수정
    @Update("UPDATE breply_table SET content = #{content} WHERE rno = #{rno}")
    void updateReply(BReplyEntity vo) throws Exception;

    // 댓글 삭제
    @Delete("DELETE FROM breply_table WHERE rno = #{rno}")
    void deleteReply(int rno) throws Exception;

    // 댓글 상세 조회
    @Select("SELECT * FROM breply_table WHERE rno = #{rno}")
    BReplyEntity getReply(int rno) throws Exception;
}*/
