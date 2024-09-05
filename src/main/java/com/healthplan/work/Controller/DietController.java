package com.healthplan.work.Controller;


import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.service.DietSerive;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.NewsEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/diet")

public class DietController {

    // 로거 삽입
    private final Logger logger = LoggerFactory.getLogger(DietController.class);

    @Autowired
    DietSerive diet;

    // 리스트 페이지
    @RequestMapping (value="/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> news() {

        Map<String, Object> rtnObj = new HashMap<>();

        // 메소드명 입력하기
        List<DietEntity> dietList = diet.selectList();
        logger.info("dietList->" + dietList.toString());

        rtnObj.put("dietList", dietList);
        return rtnObj;
    }

//
//    // 페이지 읽기 - get
//    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
//    public void read(@RequestParam("cno") int cno) throws Exception {
//
//        diet.readList(cno);
//    }

    // 페이지 읽기 - get
    @GetMapping (value = "/readPage/{cno}")
    public @ResponseBody HashMap<String, Object> read(@RequestParam("cno") int cno) throws Exception {

         Map<String, Object> readObj = new HashMap<>();

         List<DietEntity> read = diet.readPage(cno);
         logger.info("/******************* 게시글 읽기 가보자고!->" + readObj.toString());

         readObj.put("read", read);
         return new HashMap<>(readObj);
    }

    @GetMapping({"/read", "/modify"})
    public void read(long cno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){
        // requestDTO에 담아서 페이지를 돌아갈때 값들을 들고 이동함!!

        logger.info("cno: " + cno);

        DietDTO dto = diet.readPage(cno);

        model.addAttribute("dto", dto);

    }

}
