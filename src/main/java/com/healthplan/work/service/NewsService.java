package com.healthplan.work.service;

import com.healthplan.work.dao.NewsMapper;
import com.healthplan.work.vo.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    public List<NewsEntity> selectList() {
        return newsMapper.listNews();
    }
}
