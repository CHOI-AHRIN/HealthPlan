package com.healthplan.work.Controller;

import com.healthplan.work.service.ChallengeService;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/challenge")
public class ChallengeController {

    private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);

    @Autowired
    private ChallengeService challengeService;

    @GetMapping("/")
    public String index() {
        return "redirect:/challenge/challengelist";
    }

    // 챌린지 목록 표시
    @GetMapping("/challengeList")
    public Map<String, Object> clist(SearchCriteria cri) throws Exception {

        logger.info("/******************************* 챌린지 리스트 돈다 ");
        Map<String, Object> result = new HashMap<>();

        //전체검색 onchange x
        if ("".equals(cri.getSearchType())) {
            cri.setSearchType("total");
        }

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(challengeService.selectChallengeCount(cri));

        List<ChallengeEntity> clist = challengeService.selectChallengeList(cri);
        result.put("clist", clist);
        result.put("pageMaker", pageMaker);

        log.info("cri	-> " + cri);
        log.info("ChallengeList result-> " + result.toString());

        return result;

    }

    // 챌린지 글 등록
    @PostMapping("/challengeinsert")
    public String challengeInsert(ChallengeEntity challengeEntity) throws Exception {

        log.info("ChallengeEntity: mno=" + challengeEntity.getMno() +
                ", title=" + challengeEntity.getTitle() +
                ", bcontents=" + challengeEntity.getBcontents());
        log.info("Received ChallengeEntity: " + challengeEntity.toString());

        log.info("challengeInsert -> " + challengeEntity);
        challengeService.challengeInsert(challengeEntity);

        return "success";
        //return "redirect:/challenge/challengeList";
    }

    // 챌린지 상세 조회 및 수정 페이지 이동
    @GetMapping({"/challengeRead/{bno}", "/challengeModify/{bno}"})
    public ChallengeEntity challengeRead(@PathVariable("bno") int bno) throws Exception {
        // Map<String, Object> result = new HashMap<>();
        ChallengeEntity vo = challengeService.selectChallengeRead(bno);

        log.info("bno -> " + bno);
        log.info("subscribeLessionRead result -> " + vo.toString());

        // result.put("vo", vo);
        return vo;
    }

    // 챌린지 글 수정
    @PutMapping("/challengeupdate")
    public String challengeupdate(ChallengeEntity challengeEntity) throws Exception {
        challengeService.challengeUpdate(challengeEntity);
        log.info("challengeUpdate -> " + challengeEntity.toString());
        return "success";
        //return "redirect:/challenge/challengeList";
    }



    // 챌린지 글 삭제
    @DeleteMapping("/challengedelete/{bno}")
    public String challengedelete(@PathVariable("bno") int bno) throws Exception {

        log.info("challengeDelete -> " + bno);
        challengeService.challengeDelete(bno);

        return "success";
        //return "redirect:/challenge/challengeList";
    }
}
