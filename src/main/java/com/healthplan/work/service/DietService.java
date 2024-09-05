package com.healthplan.work.service;

import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.vo.DietEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {

    @Autowired
    private DietMapper dietmapper;

    // 리스트 페이지
    public List<DietEntity> selectList() {

        return dietmapper.listDiet();
    }

    // 게시글 읽기 페이지
    public List<DietEntity> readPage(Integer cno) {
        return dietmapper.readPage(cno);
    }
}
