package com.healthplan.work.dao;

import com.healthplan.work.vo.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DietMapper {

        public List<NewsEntity> list();

}
