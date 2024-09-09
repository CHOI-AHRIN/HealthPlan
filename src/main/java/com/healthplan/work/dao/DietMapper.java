package com.healthplan.work.dao;

import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.vo.Criteria;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DietMapper {

        public List<DietEntity> listDiet();

        // 게시글 조회
        public List<DietEntity> listSearchCriteria(SearchCriteria cri) throws Exception;

        // 검색 조건에 맞는 게시글의 총 개수를 계산하는 SQL 쿼리
        public int listSearchCount(Criteria cri) throws Exception;

        public List<DietEntity> readPage(int cno);

        // 등록
        public int register(DietDTO dto);

}