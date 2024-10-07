package com.healthplan.work.service;

import com.healthplan.work.dao.SubscribeMapper;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<SubscribeVO> selectSubscribeList() throws Exception {
        return subscribeMapper.selectSubscribeList();
    }

    public SubscribeVO selectSubscrLessionibeRead(int sno) throws Exception {
        subscribeMapper.updateSubscribeLessionCount(sno);
        return subscribeMapper.selectSubscribeLessionRead(sno);
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

        List<ImageDTO> imageDTOList = vo.getImageDTOList();

        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            for (ImageDTO imageDTO : imageDTOList) {
//                String orgName = imageDTO.getImgName();
                String imgName = imageDTO.getThumbnailURL();
                String imgURL  = imageDTO.getImageURL();
                String uuid    = imageDTO.getUuid();
                String path    = imageDTO.getPath();
                String imgType = imageDTO.getImgType();

                // mapper에 fileName, uuid, path 등을 활용한 로직 추가
                subscribeMapper.addAttach(imgName, imgURL, uuid, path, imgType);
            }
        }

//        Spring legacy case
//        List<ImageDTO> imageList = vo.getImageDTOList();
//        String[] files = vo.getFileid();

//        if (imageList != null && !imageList.isEmpty()) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String,Object>>();
//            int sno = vo.getSno();
//
//            for (ImageDTO idto : imageList) {
//                HashMap<String, Object> data = new HashMap<String, Object>();
//
//                data.put("fileName", "/" + idto.getPath() + "/" +idto.getImgName());
//                data.put("sno", sno);
//                listMap.add(data);
//            }
//            map.put("listMap", listMap);
//            subscribeMapper.subscribeAttach(map);
//        }
    }

    public void selectSubscribeUpdate(SubscribeVO vo) throws Exception {
        subscribeMapper.updateSubscribeLession(vo);
        subscribeMapper.deleteAttach(vo.getSno());

        List<ImageDTO> imageUpList = vo.getImageDTOList();

        if (imageUpList != null && !imageUpList.isEmpty()) {
            String sno = String.valueOf(vo.getSno());

            for (ImageDTO imageDTO : imageUpList) {
                String imgName = imageDTO.getThumbnailURL();
                String imgURL  = imageDTO.getImageURL();
                String uuid    = imageDTO.getUuid();
                String path    = imageDTO.getPath();
                String imgType = imageDTO.getImgType();

                subscribeMapper.updateAttach(imgName, imgURL, uuid, path, imgType, sno);
            }
        }
    }

    public void subscribeLessionDelete(int sno) throws Exception {
        subscribeMapper.deleteSubscribeLession(sno);
    }

    public SubscribeVO selectSubscribeRead(int sno) throws Exception {
        //subscribeMapper.updateSubscribeCount(sno);
        //return subscribeMapper.selectSubscribeLessionRead(sno);
        return null;
    }

    public List<ImageDTO> selectImageList(int sno) throws Exception {
        return subscribeMapper.selectImageList(sno);
    }
}
