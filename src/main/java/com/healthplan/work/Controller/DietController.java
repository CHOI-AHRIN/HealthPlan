package com.healthplan.work.Controller;


import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.NewsEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
    DietMapper diet;


    // test
  //  @RequestMapping(value = "/get", method = RequestMethod.GET)
    @GetMapping(value ="/test")
    @ResponseBody
    public String getReminder(){
        logger.info("/***************************************8 왜 안도니 ㅠㅠ");
        return "my reminder";
    }


    @RequestMapping (value="/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> news() {

        Map<String, Object> rtnObj = new HashMap<>();

        List<DietEntity> newsList = diet.list();
        logger.info("news->" + newsList.toString());

        rtnObj.put("news_list", newsList);
        return rtnObj;
    }

}
