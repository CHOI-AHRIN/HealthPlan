package com.healthplan.work.service;

import com.healthplan.work.dao.SubscribeMapper;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Service
@Log4j2
public class SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<SubscribeVO> selectSubscribeList() throws Exception {
        return subscribeMapper.selectSubscribeList();
    }

    public SubscribeVO selectSubscribeRead(int sno) throws Exception {
        subscribeMapper.updateSubscribeLessionCount(sno);
        return subscribeMapper.selectSubscribeRead(sno);
    }

    public void subscribeInsert(SubscribeVO vo) throws Exception {
        subscribeMapper.insertSubscribe(vo);
    }

    public void subscribeUpdate(SubscribeVO vo) throws Exception {
        subscribeMapper.updateSubscribe(vo);
    }

    public void subscribeDelete(int sno) throws Exception {
        subscribeMapper.deleteSubscribe(sno);
    }

    public List<SubscribeVO> selectSubscribeLessionList(SearchCriteria cri) throws Exception {
        return subscribeMapper.selectSubscribeLessionList(cri);
    }

    public int selectSubscribeLessionCount(SearchCriteria cri) throws Exception {
        return subscribeMapper.selectSubscribeLessionCount(cri);
    }

    public void subscribeLessionInsert(SubscribeVO vo) throws Exception {
        subscribeMapper.insertSubscribeLession(vo);
    }

    public void selectSubscribeUpdate(SubscribeVO vo) throws Exception {
        subscribeMapper.updateSubscribeLession(vo);
    }

    public void subscribeLessionDelete(int sno) throws Exception {
        subscribeMapper.deleteSubscribeLession(sno);
    }
}
