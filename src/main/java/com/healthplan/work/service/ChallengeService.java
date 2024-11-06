package com.healthplan.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.SearchCriteria;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ChallengeService {

    @Autowired
    private ChallengeMapper challengeMapper;

    public List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeList(cri);
    }

    //챌린지 Count
    public int selectChallengeCount(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeCount(cri);
    }

    //챌린지 읽기
    public ChallengeEntity selectChallengeRead(int bno) throws Exception {
        challengeMapper.updateChallengeCount(bno);
        // logger.info("29 Line: SubscribeSerive Lession Count ====> " + bno);
        return challengeMapper.selectChallengeRead(bno);
    }

    // 챌린지 작성
    public void challengeInsert(ChallengeEntity vo) throws Exception {
        challengeMapper.insertChallenge(vo);

        List<ImageDTO> imageDTOList = vo.getImageDTOList();
        challengeMapper.deleteAttach(vo.getSno());

        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            for (ImageDTO imageDTO : imageDTOList) {
                String imgName = imageDTO.getThumbnailURL();
                String imgURL = imageDTO.getImageURL();
                String uuid = imageDTO.getUuid();
                String path = imageDTO.getPath();
                String imgType = imageDTO.getImgType();
                
                challengeMapper.addAttach(imgName, imgURL, uuid, path, imgType);
            }
        }
    }
    
    // 챌린지 수정
    public void challengeUpdate(ChallengeEntity vo) throws Exception {
        challengeMapper.updateChallenge(vo);

        List<ImageDTO> imageUpList = vo.getImageDTOList();
        challengeMapper.deleteAttach(vo.getSno());

        if (imageUpList != null && !imageUpList.isEmpty()) {
            String sno = String.valueOf(vo.getSno());

            for (ImageDTO imageDTO : imageUpList) {
                String imgName = imageDTO.getThumbnailURL();
                String imgURL = imageDTO.getImageURL();
                String uuid = imageDTO.getUuid();
                String path = imageDTO.getPath();
                String imgType = imageDTO.getImgType();

                challengeMapper.updateAttach(imgName, imgURL, uuid, path, imgType, sno);
            }
        }
    }

    // 챌린지 삭제
    public void challengeDelete(int bno) throws Exception {
        challengeMapper.deleteChallenge(bno);
    }





}
