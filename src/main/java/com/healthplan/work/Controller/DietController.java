package com.healthplan.work.Controller;



import com.healthplan.work.dto.DietDTO;
import com.healthplan.work.service.DietService;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.NewsEntity;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /* 무조건 데이터를 반환하고, JSON 형태로 맞춰준다
     반환되는 json 형태를 리액트에 넣어서 사용해준다
     
     
    */
    
    
    // 리스트 페이지
    @RequestMapping (value="/list")
    public @ResponseBody Map<String, Object> news(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception {

        Map<String, Object> rtnObj = new HashMap<>();

        // 메소드명 입력하기
        List<DietEntity> dietList = diet.selectList();
        logger.info("dietList->" + dietList.toString());

        rtnObj.put("dietList", dietList);


        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        logger.info("/*******************페이지 메이커에 셋크리~" + cri.toString());

        pageMaker.setTotalCount(diet.listSearchCount(cri));
        logger.info("/*******************페이지 메이커에 셋토탈카운트~" + cri.toString());
        model.addAttribute("pageMaker", pageMaker);


        return rtnObj;
    }


    // 게시글 읽기 - get, JSON 형식으로 반환
    @SneakyThrows // 명시적 예외처리
    @GetMapping("read/{cno}")
    public @ResponseBody Map<String, Object> getDietRead(@PathVariable Integer cno) {

        Map<String, Object> dietRead = new HashMap<>();
        logger.info("/****************** 게시글 보여줘"+dietRead);

        List<DietEntity> dietRead2 = diet.readPage(cno);
        dietRead.put("dietRead", dietRead2);

        return dietRead;
    }

    // 게시글 작성
    @GetMapping("/register")
    public void registerGet() throws Exception {
        logger.info ("/*************************************************** 작성 페이지 돈다잉");
    }

/*    @PostMapping("register")
    public void registerPost(DietDTO dto, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("/********************* dto 좀 보자" + dto);
        
        // 새로 추가된 게시글 번호
        diet.register(dto);


    }*/

    // 게시글 작성 POST
    @PostMapping("/register")
    public ResponseEntity<String> registerDiet(@RequestBody DietDTO dto) throws Exception {

        int result = diet.register(dto); // 쿼리 실행 결과가 반환됨

        if (result > 0) {
            return new ResponseEntity<>("Diet post registered successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to register post.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 게시글 수정
    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public void modifyPagingGET(int cno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

        model.addAttribute("CommunityVO", diet.readPage(cno));
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagingPOST(DietEntity dietentity, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

        logger.info(cri.toString());
        diet.modify(dietentity);

        rttr.addAttribute("page", cri.getPage());
        rttr.addAttribute("perPageNum", cri.getPerPageNum());
        rttr.addAttribute("searchType", cri.getSearchType());
        rttr.addAttribute("keyword", cri.getKeyword());

        rttr.addFlashAttribute("msg", "SUCCESS");

        logger.info(rttr.toString());

        return "msg";

        // return "redirect:/community/listAll2";
    }


    // 게시글 삭제
    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String remove(@RequestParam("cno") int cno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

        diet.remove(cno);

        rttr.addAttribute("page", cri.getPage());
        rttr.addAttribute("perPageNum", cri.getPerPageNum());
        rttr.addAttribute("searchType", cri.getSearchType());
        rttr.addAttribute("keyword", cri.getKeyword());

        rttr.addFlashAttribute("msg", "SUCCESS");

        return "msg";

    }
}
