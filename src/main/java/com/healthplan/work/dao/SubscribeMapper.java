package com.healthplan.work.dao;

import com.healthplan.work.vo.SReplyVO;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeMapper {

    public List<SubscribeVO> selectSubscribeList() throws Exception;

    public SubscribeVO selectSubscribeRead(int sno) throws Exception;

    public void insertSubscribe(SubscribeVO vo) throws Exception;

    public void updateSubscribe(SubscribeVO vo) throws Exception;

    public void deleteSubscribe(int sno) throws Exception;

    public List<SubscribeVO> selectSubscribeLessionList(SearchCriteria cri) throws Exception;

    public int selectSubscribeLessionCount(SearchCriteria cri) throws Exception;

    public void insertSubscribeLession(SubscribeVO vo) throws Exception;

    public void updateSubscribeLession(SubscribeVO vo) throws Exception;

    public void deleteSubscribeLession(int sno) throws Exception;

    public void updateSubscribeLessionCount(int sno) throws Exception;

    public void updateReplyCnt(SReplyVO vo) throws Exception;

    public void updateReplyCntSubtraction(int sno) throws Exception;
}