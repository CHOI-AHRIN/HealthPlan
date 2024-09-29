package com.healthplan.work.Controller;

import com.healthplan.work.service.BReplyService;
import com.healthplan.work.vo.BReplyEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/breplies")
public class BReplyController {

    @Autowired
    private BReplyService breplyService;

    /**
     * 댓글 목록 조회
     *
     * @param bno 게시글 번호
     * @return 댓글 목록
     * @throws Exception 예외
     */
    @GetMapping("/list/{bno}")
    public ResponseEntity<List<BReplyEntity>> list(@PathVariable("bno") int bno) throws Exception {
        log.info("bno -> " + bno);

        ResponseEntity<List<BReplyEntity>> entity = null;
        try {
            List<BReplyEntity> replyList = breplyService.listReply(bno);
            entity = new ResponseEntity<>(replyList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 댓글 등록
     *
     * @param vo 댓글 객체
     * @return 응답 메시지
     * @throws Exception 예외
     */
    @PostMapping("/add")
    public ResponseEntity<String> insert(@RequestBody BReplyEntity vo) throws Exception {
        log.info("add -> " + vo.toString());

        ResponseEntity<String> entity = null;
        try {
            breplyService.addReply(vo);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 댓글 수정
     *
     * @param rno 댓글 번호
     * @param vo  댓글 객체
     * @return 응답 메시지
     */
    @PutMapping("/update/{rno}")
    public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody BReplyEntity vo) {
        log.info("rno -> " + rno);

        ResponseEntity<String> entity = null;
        try {
            vo.setRno(rno);
            breplyService.modifyReply(vo);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 댓글 삭제
     *
     * @param rno 댓글 번호
     * @return 응답 메시지
     */
    @DeleteMapping("/delete/{rno}")
    public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
        log.info("rno -> " + rno);

        ResponseEntity<String> entity = null;
        try {
            breplyService.removeReply(rno);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
