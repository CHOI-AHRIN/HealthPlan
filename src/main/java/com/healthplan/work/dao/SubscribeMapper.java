package com.healthplan.work.dao;

import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.SReplyVO;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * The interface Subscribe mapper.
 */
@Mapper
public interface SubscribeMapper {

    /**
     * Select subscribe list list.
     *
     * @param cri the cri
     * @return the list
     * @throws Exception the exception
     */
    public List<SubscribeVO> selectSubscribeList(SearchCriteria cri) throws Exception;

    /**
     * Select subscribe lession read subscribe vo.
     *
     * @param sno the sno
     * @return the subscribe vo
     * @throws Exception the exception
     */
    public SubscribeVO selectSubscribeLessionRead(int sno) throws Exception;

    /**
     * Insert subscribe.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void insertSubscribe(SubscribeVO vo) throws Exception;

    /**
     * Update subscribe.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void updateSubscribe(SubscribeVO vo) throws Exception;

    /**
     * Delete subscribe.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void deleteSubscribe(int sno) throws Exception;

    /**
     * Select subscribe lession list list.
     *
     * @param cri the cri
     * @return the list
     * @throws Exception the exception
     */
    public List<SubscribeVO> selectSubscribeLessionList(SearchCriteria cri) throws Exception;

    /**
     * Select subscribe lession count int.
     *
     * @param cri the cri
     * @return the int
     * @throws Exception the exception
     */
    public int selectSubscribeLessionCount(SearchCriteria cri) throws Exception;

    /**
     * Insert subscribe lession.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void insertSubscribeLession(SubscribeVO vo) throws Exception;

    /**
     * Update subscribe lession.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void updateSubscribeLession(SubscribeVO vo) throws Exception;

    /**
     * Delete subscribe lession.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void deleteSubscribeLession(int sno) throws Exception;

    /**
     * Update subscribe lession count.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void updateSubscribeLessionCount(int sno) throws Exception;

    /**
     * Update reply cnt.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void updateReplyCnt(SReplyVO vo) throws Exception;

    /**
     * Update reply cnt subtraction.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void updateReplyCntSubtraction(int sno) throws Exception;

    /**
     * Subscribe attach.
     *
     * @param map the map
     * @throws Exception the exception
     */
    public void subscribeAttach(HashMap<String, Object> map) throws Exception;

    /**
     * Subscribe string attach.
     *
     * @param csno    the csno
     * @param imgName the img name
     * @throws Exception the exception
     */
    public void subscribeStringAttach(String csno, String imgName) throws Exception;

    /**
     * Select image list list.
     *
     * @param sno the sno
     * @return the list
     * @throws Exception the exception
     */
    public List<ImageDTO> selectImageList(int sno) throws Exception;

    /**
     * Add attach.
     *
     * @param imgName the img name
     * @param imgURL  the img url
     * @param uuid    the uuid
     * @param path    the path
     * @param imgType the img type
     * @throws Exception the exception
     */
    public void addAttach(String imgName, String imgURL, String uuid, String path, String imgType) throws Exception;

    /**
     * Delete attach.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void deleteAttach(int sno) throws Exception;

    /**
     * Update attach.
     *
     * @param imgName the img name
     * @param imgURL  the img url
     * @param uuid    the uuid
     * @param path    the path
     * @param imgType the img type
     * @param sno     the sno
     * @throws Exception the exception
     */
    public void updateAttach(String imgName, String imgURL, String uuid, String path, String imgType, String sno) throws Exception;

    /**
     * Select subscribe count int.
     *
     * @param cri the cri
     * @return the int
     * @throws Exception the exception
     */
    public int selectSubscribeCount(SearchCriteria cri) throws Exception;

    /**
     * Update subscribe count.
     *
     * @param sno the sno
     * @throws Exception the exception
     */
    public void updateSubscribeCount(int sno) throws Exception;

    /**
     * Select subscribe read subscribe vo.
     *
     * @param sno the sno
     * @return the subscribe vo
     * @throws Exception the exception
     */
    public SubscribeVO selectSubscribeRead(int sno) throws Exception;

    public List<ImageDTO> selectMainImage(int sno) throws Exception;
}