package com.healthplan.work.dao;

import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.vo.MemberEntity;

import java.util.Map;

public interface MemberMapper {

    public MemberEntity login(LoginDTO dto) throws Exception;

    public void keepLogin(Map<String, Object> paramMap) throws Exception;

    public MemberEntity checkUserWithSessionKey(String sessionId);

    // 회원가입
    public void create(MemberEntity vo) throws Exception;

    // 아이디 중복체크
    public int uuidcheck(MemberEntity vo) throws Exception;

    // 현재 mno 조회
    public int currval() throws Exception;

    // 포인트 입력
    public void setpoint(Integer mno) throws Exception;

    // 회원정보 페이지
    public MemberEntity profile(String uuid) throws Exception;

    // 회원정보 수정
    public MemberEntity profileModify(String uuid) throws Exception;

    // 회원정보 삭제
    public void delete(String uuid) throws Exception;

    // 회원 탈퇴 시 비밀번호 체크
    public int upwcheck(MemberEntity vo) throws Exception;


}
