package com.healthplan.work.service;

import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietMapper dietmapper;

    // 리스트 페이지
    public List<DietEntity> selectList() throws Exception {

        return dietmapper.listDiet();
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return 0;
    }

    // 검색 조건에 맞는 게시글의 총 개수를 계산하는 SQL 쿼리
    public List<DietEntity> listSearchCriteria(SearchCriteria cri) throws Exception {
        return dietmapper.listSearchCriteria(cri);
    }



    // 게시글 읽기 페이지
    public List<DietEntity> readPage(Integer cno) throws Exception {
        return dietmapper.readPage(cno);
    }

    // 게시글 작성
    @Transactional
    public int register(DietDTO dto) throws Exception{
        return dietmapper.register(dto); // 얘가 int 반환할텐데!
    }

    // 게시판 수정
    public void modify (DietEntity dietentity) throws Exception{

    }


    // 게시판 삭제, 게시판 번호에 해당하는 것을 삭제
    public void remove(Integer cno) throws Exception{

    }

}
