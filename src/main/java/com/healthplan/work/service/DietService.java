package com.healthplan.work.service;

import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DietService {

/*    @Autowired
    private DietMapper dietmapper;*/

    // 리스트 페이지
    public List<DietEntity> selectList()throws Exception;

    // 검색 조건에 맞는 게시글의 총 개수를 계산하는 SQL 쿼리
    public int listSearchCount(SearchCriteria cri) throws Exception;

    // 게시글 읽기 페이지
    public List<DietEntity> readPage(Integer cno) throws Exception;



    // 게시글 작성
    public int register(DietDTO dto) throws Exception;



    // 게시판 수정
    public void modify (DietEntity dietentity) throws Exception;


    // 게시판 삭제, 게시판 번호에 해당하는 것을 삭제
    public void remove(Integer cno) throws Exception;

}
