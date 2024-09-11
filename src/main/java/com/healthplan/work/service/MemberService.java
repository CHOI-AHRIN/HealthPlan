package com.healthplan.work.service;

import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.vo.MemberEntity;

import java.util.Date;
import java.util.List;

public interface MemberService {

    
    // 9/11 MemberEntity 타입에서 List 타입으로 변환함
    public List<MemberEntity> login(LoginDTO dto) throws Exception;

    public void keepLogin(String uuid, String sessionId, Date next)throws Exception;

    public MemberEntity checkLoginBefore(String value);

    // 회원가입
    public void join(MemberEntity vo )throws Exception;

    // 아이디 중복체크
    public int uuidcheck(MemberEntity vo)throws Exception;

    // 회원정보 페이지
    public MemberEntity profile(String uuid) throws Exception;

    // 회원정보 수정
    public void profileModify(String upw, String phone, String email,  String uuid) throws Exception;

    //회원정보 삭제
    public void delete (String uuid) throws Exception;

    // 회원 탈퇴 시 비밀번호 체크
    public int upwcheck(MemberEntity vo)throws Exception;

    
}
