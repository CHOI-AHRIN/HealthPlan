package com.healthplan.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.MemberEntity;
import com.healthplan.work.vo.PointDTO;
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

    // 챌린지 Count
    public int selectChallengeCount(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeCount(cri);
    }

    // //ImageDTO (MainImage)
    // public List<ImageDTO> selectMainImage(int bno) throws Exception {
    //     return challengeMapper.selectMainImage(bno);
    // }

    //ImageDTO (ImageList)
    public List<ImageDTO> selectImageList(int bno) throws Exception {
        return challengeMapper.selectImageList(bno);
    }

    // 챌린지 읽기
    public ChallengeEntity selectChallengeRead(int bno) throws Exception {
        challengeMapper.updateChallengeCount(bno);
        return challengeMapper.selectChallengeRead(bno);
    }

    public List<ChallengeEntity> selectChallengeListWithImages(SearchCriteria cri) throws Exception {
        List<ChallengeEntity> clist = challengeMapper.selectChallengeList(cri);
        for (ChallengeEntity challenge : clist) {
            List<ImageDTO> images = challengeMapper.selectImageList(challenge.getBno());
            challenge.setImageDTOList(images); 
        }
        return clist;
    }    

    // 챌린지 작성
    public void challengeInsert(ChallengeEntity vo) throws Exception {
        challengeMapper.insertChallenge(vo);
        int bno = vo.getBno();

        List<ImageDTO> imageDTOList = vo.getImageDTOList();

        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            for (ImageDTO imageDTO : imageDTOList) {
                String imgName = imageDTO.getThumbnailURL();
                String imgURL = imageDTO.getImageURL();
                String uuid = imageDTO.getUuid();
                String path = imageDTO.getPath();
                String imgType = imageDTO.getImgType();

                challengeMapper.addAttach(imgName, imgURL, uuid, path, imgType, bno);
            }
        }
    }

    // 챌린지 수정
    public void challengeUpdate(ChallengeEntity vo) throws Exception {
        challengeMapper.updateChallenge(vo);

        List<ImageDTO> imageUpList = vo.getImageDTOList();
        challengeMapper.deleteAttach(vo.getBno());

        if (imageUpList != null && !imageUpList.isEmpty()) {
            String bno = String.valueOf(vo.getBno());

            for (ImageDTO imageDTO : imageUpList) {
                String imgName = imageDTO.getThumbnailURL();
                String imgURL = imageDTO.getImageURL();
                String uuid = imageDTO.getUuid();
                String path = imageDTO.getPath();
                String imgType = imageDTO.getImgType();

                challengeMapper.updateAttach(imgName, imgURL, uuid, path, imgType, bno);
            }
        }
    }

    // 챌린지 삭제
    public void challengeDelete(int bno) throws Exception {
        challengeMapper.deleteChallenge(bno);
    }

    // 포인트 적립
    public void addPoint(PointDTO point) throws Exception {
        challengeMapper.addPoint(point);
    }

    // 랭킹
    public List<MemberEntity> getChallengeRanking(MemberEntity mem) throws Exception {
        // 상위 3명의 회원을 댓글 수가 많은 순으로 조회
        return challengeMapper.cRank(mem);
    }
}