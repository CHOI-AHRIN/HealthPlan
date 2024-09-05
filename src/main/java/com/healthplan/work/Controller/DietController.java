package com.healthplan.work.Controller;



import com.healthplan.work.service.DietService;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.NewsEntity;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/diet")

public class DietController {

    // 로거 삽입
    private final Logger logger = LoggerFactory.getLogger(DietController.class);

    @Autowired
    DietService diet;

    // 리스트 페이지
    @RequestMapping (value="/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> news(@ModelAttribute("cri")SearchCriteria cri, Model model) {

        Map<String, Object> rtnObj = new HashMap<>();

        // 메소드명 입력하기
        List<DietEntity> dietList = diet.selectList();
        logger.info("dietList->" + dietList.toString());

        rtnObj.put("dietList", dietList);


/*
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        logger.info("/*******************페이지 메이커에 셋크리~" + cri.toString());

        pageMaker.setTotalCount(diet.listSearchCount(cri));
        logger.info("/*******************페이지 메이커에 셋토탈카운트~" + cri.toString());
        model.addAttribute("pageMaker", pageMaker);
*/


        return rtnObj;
    }


    // 게시글 읽기 - get, JSON 형식으로 반환
    @GetMapping("read/{cno}")
    public @ResponseBody Map<String, Object> getDietRead(@PathVariable Integer cno) {

        Map<String, Object> dietRead = new HashMap<>();
        logger.info("/****************** 게시글 보여줘"+dietRead);

        List<DietEntity> dietList = diet.readPage(cno);
        dietRead.put("dietList", dietList);

        return dietRead;
    }


}
