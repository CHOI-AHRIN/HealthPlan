package com.healthplan.work.Controller;

import com.healthplan.work.service.BReplyService;
import com.healthplan.work.vo.BReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies")
public class BReplyController {

    @Autowired
    private BReplyService bReplyService;

    // 댓글 목록 조회
    @GetMapping("/post/{bno}")
    public List<Map<String, Object>> getRepliesByBno(@PathVariable("bno") int bno) throws Exception {
        return bReplyService.getRepliesByBno(bno);
    }

    // 댓글 등록
    @PostMapping
    public BReplyEntity insert(@RequestBody BReplyEntity bReplyEntity) throws Exception {
        bReplyService.insertReply(bReplyEntity);
        return bReplyEntity;
    }

    // 댓글 상세 조회
    @GetMapping("/{rno}")
    public BReplyEntity read(@PathVariable("rno") int rno) throws Exception {
        BReplyEntity reply = bReplyService.getReply(rno);
        return reply;
    }

    // 댓글 수정
    @PutMapping("/{rno}")
    public void update(@PathVariable("rno") int rno, @RequestBody BReplyEntity bReplyEntity) throws Exception {
        bReplyEntity.setRno(rno);
        bReplyService.updateReply(bReplyEntity);
    }

    // 댓글 삭제
    @DeleteMapping("/{rno}")
    public void delete(@PathVariable("rno") int rno) throws Exception {
        bReplyService.deleteReply(rno);
    }
}

