package com.healthplan.work.Controller;

import com.healthplan.work.service.BReplyService;
import com.healthplan.work.vo.BReplyEntity;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/replies")
public class BReplyController {

    @Autowired
    private BReplyService service;
    
/*
    @Resource(name = "uploadPath")
    private String uploadPath;
*/

    // 댓글 등록
    @RequestMapping(value = "/replyregister", method = RequestMethod.POST)
    public ResponseEntity<String> register(
            @RequestParam(value = "bno", required = true) Integer bno,
            @RequestParam(value = "rComment", required = true) String rComment,
            @RequestParam(value = "fileName", required = false) MultipartFile file,
            @RequestParam(value = "mno", required = true) Integer mno) {
        
        System.out.println("/*** /replies/replyregister 실행...");
        System.out.println("Received bno: " + bno);
        System.out.println("Received rComment: " + rComment);
        System.out.println("Received mno: " + mno);
        if (file != null) {
            System.out.println("Received file: " + file.getOriginalFilename());
        }

        ResponseEntity<String> entity = null;
        try {
            // mno가 null인 경우 예외 처리
            if (mno == null) {
                throw new IllegalArgumentException("mno 값이 null입니다.");
            }

            String savedFileName = null;
            if (file != null && !file.isEmpty()) {
                savedFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
                System.out.println("Saved fileName: " + savedFileName);
            }

            BReplyEntity vo = new BReplyEntity();
            vo.setBno(bno);
            vo.setrComment(rComment);
            vo.setFileName(savedFileName != null ? savedFileName.substring(0, Math.min(savedFileName.length(), 50)) : null);
            vo.setMno(mno);

            service.addReply(vo); // 댓글 등록
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 파일 업로드 처리
    private String uploadFile(String originalName, byte[] fileData) throws Exception {
        UUID uid = UUID.randomUUID();
        String savedName = uid.toString() + "_" + originalName;
        File target = new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);
        return savedName;
    }

//-----------------------------------------------------------------------------------------------------

    // 전체 댓글 목록 가져오기
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<BReplyEntity>> listAll() {
        try {
            List<BReplyEntity> replies = service.listReplies();
            return new ResponseEntity<>(replies, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 수정
    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestParam("rComment") String rComment) {
        ResponseEntity<String> entity = null;
        try {
            BReplyEntity vo = new BReplyEntity();
            vo.setRno(rno);
            vo.setrComment(rComment); // 수정된 메서드 이름
            service.modifyReply(vo);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 삭제
    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {
        ResponseEntity<String> entity = null;
        try {
            service.removeReply(rno);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

   /* // 페이지별 댓글 목록 가져오기
    @RequestMapping(value = "/pages/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("page") int page) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Criteria cri = new Criteria();
            cri.setPage(page);

            List<BReplyVO> replies = service.listRepliesPage(cri);
            int total = service.countTotalReplies();

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCri(cri);
            pageMaker.setTotalCount(total);

            Map<String, Object> result = new HashMap<>();
            result.put("comments", replies);
            result.put("pageMaker", pageMaker);

            entity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 최근 10개의 댓글 목록 가져오기
    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    public @ResponseBody List<BReplyEntity> listRecentReplies() {
        try {
            return service.listRecentReplies(10); // 최근 10개의 댓글을 가져오는 메서드 호출
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } */
}
