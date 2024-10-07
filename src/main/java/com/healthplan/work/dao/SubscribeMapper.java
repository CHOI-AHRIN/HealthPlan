package com.healthplan.work.dao;

import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.SReplyVO;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SubscribeMapper {

    public List<SubscribeVO> selectSubscribeList() throws Exception;

    public SubscribeVO selectSubscribeLessionRead(int sno) throws Exception;

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

    public void subscribeAttach(HashMap<String, Object> map) throws Exception;

    public void subscribeStringAttach(String csno, String imgName) throws Exception;

    public List<ImageDTO> selectImageList(int sno) throws Exception;

    public void addAttach(String imgName, String imgURL, String uuid, String path, String imgType) throws Exception;

    public void deleteAttach(int sno) throws Exception;

    public void updateAttach(String imgName, String imgURL, String uuid, String path, String imgType, String sno) throws Exception;
}
