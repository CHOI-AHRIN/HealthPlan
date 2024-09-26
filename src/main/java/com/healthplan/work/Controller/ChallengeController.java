package com.healthplan.work.Controller;

import com.healthplan.work.service.ChallengeService;
import com.healthplan.work.vo.ChallengeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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
    @GetMapping("/challengelist")
    public Map<String, Object> list() throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ChallengeEntity> list = challengeService.selectChallengeList();
        logger.info("challengeList -> " + list.toString());

        result.put("clist", list);
        return result;
    }

    // 챌린지 글 등록
    @PostMapping("/challengeinsert")
    public String insert(@RequestBody ChallengeEntity challengeEntity) throws Exception {
        logger.info("챌린지 인서트 포스트 돈다");
        logger.info("ChallengeEntity: mno=" + challengeEntity.getMno() +
                ", title=" + challengeEntity.getTitle() +
                ", bcontents=" + challengeEntity.getBcontents());
        logger.info("Received ChallengeEntity: " + challengeEntity.toString());

        challengeService.challengeInsert(challengeEntity);
        logger.info("challengeInsert -> " + challengeEntity.toString());

        // return "success";
       return "redirect:/challenge/challengelist";
    }

    // 챌린지 상세 조회 및 수정 페이지 이동
    @GetMapping({"/challengeread/{bno}", "/challengemodify/{bno}"})
    public Map<String, Object> read(@PathVariable("bno") int bno) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ChallengeEntity vo = challengeService.selectChallengeRead(bno);
        logger.info("challengeRead -> " + vo.toString());

        result.put("vo", vo);
        return result;
    }

    // 챌린지 글 수정
    @PutMapping("/challengeupdate")
    public String update(@RequestBody ChallengeEntity challengeEntity) throws Exception {
        challengeService.challengeUpdate(challengeEntity);
        logger.info("challengeUpdate -> " + challengeEntity.toString());

        return "success";
        // return "redirect:/challenge/challengelist";
    }

    // 챌린지 글 삭제
    @DeleteMapping("/challengedelete/{bno}")
    public String delete(@PathVariable("bno") int bno) throws Exception {
        challengeService.challengeDelete(bno);
        logger.info("challengeDelete -> " + bno);

        return "success";
        // return "redirect:/challenge/challengelist";
    }
}
