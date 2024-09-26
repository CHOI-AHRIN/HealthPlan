package com.healthplan.work.Controller;

import com.healthplan.work.service.SReplyService;
import com.healthplan.work.vo.SReplyVO;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SReplyController : 구독 > 댓글
 * The type S reply controller.
 */
@RestController
@Log4j2
@RequestMapping("/sreplies")
public class SReplyController {

    @Autowired
    private SReplyService sreplyService;

    /*****************************************************************************************************************
     * sReplyList : 구독/강의 댓글 select list
     *
     * List response entity.
     *
     * @param sno the sno
     * @return the response entity
     * @throws Exception the exception
     */
    @GetMapping("/list/{sno}")
    public ResponseEntity<List<SReplyVO>> list(@PathVariable("sno") int sno) throws Exception {
        log.info("sno -> " + sno);

        ResponseEntity<List<SReplyVO>> entity = null;
        try {
            entity = new ResponseEntity<>(sreplyService.listReply(sno), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * sReplyInsert : 구독/강의 댓글등록 insert
     * Insert response entity.
     *
     * @param vo the vo
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("/add")
    public ResponseEntity<String> insert(@RequestBody @NotNull SReplyVO vo) throws Exception {
        log.info("add -> " + vo.toString());

        ResponseEntity<String> entity = null;
        try {
            sreplyService.addReply(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * sReplyUpdate : 구독/강의 댓글수정 update
     * Update response entity.
     *
     * @param rno the rno
     * @param vo  the vo
     * @return the response entity
     */
    @PutMapping("/update/{rno}")
    public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody SReplyVO vo) {
        log.info("rno -> " + rno);

        ResponseEntity<String> entity = null;
        try {
            vo.setRno(rno);
            sreplyService.modifyReply(vo);

            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * sReplyDelete : 구독/강의 댓글삭제 delete
     * Delete response entity.
     *
     * @param rno the rno
     * @return the response entity
     */
    @DeleteMapping("/delete/{rno}")
    public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
        log.info("rno -> " + rno);

        ResponseEntity<String> entity = null;
        try {
            sreplyService.removeReply(rno);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}