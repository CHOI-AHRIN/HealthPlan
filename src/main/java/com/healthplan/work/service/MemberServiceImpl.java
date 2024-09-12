/*
package com.healthplan.work.service;


import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.vo.MemberEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl {

    // 회원 테이블
    @Autowired
    private MemberMapper mapper;
    // 커뮤니티 테이블
    @Autowired
    private DietMapper dmapper;
    // 커뮤니티 댓글 테이블
//    @Autowired
//    private CReplyMapper cmapper;
//    // 구독 테이블
//    @Autowired
//    private SubscribeMapper smapper;
//    // 구독 댓글 테이블
//    @Autowired
//    private SReplyMapper ssmapper;
//    // 보드 테이블
//    @Autowired
//    private FAQMapper fmapper;
//    // 보드 댓글 테이블
//    private BReplyMapper bmapper;

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);


    public List<MemberEntity> login(LoginDTO dto) throws Exception {

        MemberEntity MemberEntity = mapper.login(dto);

        if (MemberEntity == null) {
            logger.warn("로그인 실패: 해당 사용자를 찾을 수 없습니다. {}", dto);
            return null; // 로그인 에러 페이지로 넘기기 위해 리턴 널 추가
        }
        // System.out.println("/*** MemberEntity=" + MemberEntity.toString());
        System.out.println("/*** MemberEntity=" + MemberEntity.toString());
        // 9/11 주석처리 > return MemberEntity;
        // return mapper.login(dto); // uid, upw, uname
    }


    public void join(MemberEntity vo) throws Exception {
        mapper.create(vo);

        int pmno = mapper.currval();

        mapper.setpoint(pmno);

        logger.info("join 서비스에서 알림!!!!!!!!!!!!!!!!!!!       " + vo);
        logger.info("join 서비스에서 포인트 충전 알림!!!!!!!!!!!!!!!!!!!       " + pmno);

    }

    // 아이디 중복 체크

    public int uuidcheck(MemberEntity vo) throws Exception{
        int result = mapper.uuidcheck(vo);
        return result;
    }


    public void keepLogin(String uuid, String sessionId, Date next) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uuid", uuid);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        mapper.keepLogin(paramMap);

    }


    public MemberEntity checkLoginBefore(String value) {

        return mapper.checkUserWithSessionKey(value);
    }

    // 회원정보 페이지

    public MemberEntity profile(String uuid) throws Exception {
        return mapper.profile(uuid);
    }

    // 회원정보 수정

    public void profileModify(String upw,  String phone, String email,String uuid) throws Exception {

        logger.info("/******************** 회원정보 수정 서비스 임플리먼트 도랏다");
        String sql = "update member set upw=?, phone=?, email=? where uuid=?";

        try (Connection connection = dataSource.getConnection(); // 데이터베이스와 연결을 설정하는 역할
             PreparedStatement ps = connection.prepareStatement(sql)) {
            //  SQL 쿼리를 실행할 준비
            // connection > 데이터베이스와의 연결, 데이터베이스에 대한 SQL 명령을 실행하는 데 사용
            // prepareStatement(sql) > 주어진 SQL 쿼리를 실행할 준비를 하는 PreparedStatement 객체를 반환

            ps.setString(1,upw);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, uuid);

            ps.executeUpdate();
            // 데이터베이스에 대해 INSERT, UPDATE, DELETE와 같은 SQL 문을 실행할 때 사용
            // 실행된 SQL 문에 의해 영향을 받은 행(row)의 수를 반환

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database update error");
        }

        logger.info("/******************** 회원정보 수정 서비스 임플리먼트 돌고왔다");

    }

    // 회원정보 삭제
    public void delete (String uuid) throws Exception{
        // 보드 테이블 게시글 삭제
        //fmapper.ndelete(uuid);
        // 보드 테이블 댓글 삭제
        // bmapper.ndelete(uuid);
        // 구독 테이블 게시글 삭제
        // smapper.ndelete(uuid);
        // 구독 테이블 댓글 삭제
        // ssmapper.ndelete(uuid);
        // 커뮤니티 테이블 게시글 삭제
       //dmapper.ndelete(uuid);
        // 커뮤니티 테이블 댓글 삭제
       // cmapper.ndelete(uuid);
        mapper.delete(uuid);

    }
    // 회원 탈퇴 시 비밀번호 체크
    public int upwcheck(MemberEntity vo)throws Exception{
        int result = mapper.upwcheck(vo);
        return result;
    }
}
*/
