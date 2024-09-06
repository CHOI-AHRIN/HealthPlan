package com.healthplan.work.service;

import java.util.List;

import com.healthplan.work.dao.ExerciseMapper;
import com.healthplan.work.vo.ExerciseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ExerciseService {
   @Autowired
    ExerciseMapper exerciseMapper;

    public List<ExerciseEntity> readPage(int cno) {
        return exerciseMapper.readPage(cno);
    }
}
