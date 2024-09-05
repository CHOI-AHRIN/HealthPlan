package com.healthplan.work.dao;

import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DietMapper {

        public List<DietEntity> listDiet();

        public List<DietEntity> readPage(int cno);

        public int register(DietDTO dto);

}
