package com.healthplan.work.Controller;

import com.healthplan.work.service.BReplyService;
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
@RequestMapping(value = "/challenge")
public class ChallengeController {

    private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private BReplyService replyService;

    @GetMapping("/")
    public String index() {
        return "redirect:/challenge/challengelist";
    }

    // 챌린지 목록 표시
    @GetMapping("/challengeList")
        public Map<String, Object> list() throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ChallengeEntity> list = challengeService.selectChallengeList();
        logger.info("challengeList -> " + list.toString());

            result.put("clist", list);
            return result;
        }

    // 챌린지 글 등록
    @PostMapping("/challengeReg")
    public String insert(ChallengeEntity challengeEntity) throws Exception {
        challengeService.ChallengeReg(challengeEntity);
        logger.info("challengeReg -> " + challengeEntity.toString());

        return "redirect:/challenge/challengelist";
        }

    @GetMapping({"/challengeRead/{bno}", "/challengeModify/{bno}"})
    public Map<String, Object> read(@PathVariable("bno") int bno) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ChallengeEntity vo = challengeService.selectChallengeRead(bno); // 서비스 수정해야함
        logger.info("challengeRead -> " + vo.toString());

        result.put("vo", vo);
        return result;
        }

    @PutMapping("/challengeUpdate")
    public String update(ChallengeEntity challengeEntity) throws Exception {
        challengeService.challengeUpdate(challengeEntity);  // 서비스 수정해야함
        logger.info("challengeUpdate -> " + challengeEntity.toString());

        return "redirect:/challenge/challengelist";
    }


    // 챌린지 글 삭제
    @DeleteMapping("/challengeDelete/{bno}")
    public String delete(@PathVariable("bno") int bno) throws Exception {
        challengeService.challengeDelete(bno); // 서비스 수정해야함
        logger.info("challengeDelete -> " + bno);

        return "redirect:/challenge/challengelist";
    }


}