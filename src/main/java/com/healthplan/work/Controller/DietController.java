package com.healthplan.work.Controller;


import com.healthplan.work.dao.DietMapper;
import com.healthplan.work.vo.NewsEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/community")

public class DietController {

    // 로거 삽입
    private static final Logger logger = LoggerFactory.getLogger(DietController.class);

    @Autowired
    DietMapper diet;


    // test
  //  @RequestMapping(value = "/get", method = RequestMethod.GET)
    @GetMapping
    @ResponseBody
    public String getReminder(){
        return "my reminder";
    }


    @RequestMapping (value="/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> news() {

        Map<String, Object> rtnObj = new HashMap<>();

        List<NewsEntity> newsList = diet.list();
        logger.info("news->" + newsList.toString());

        rtnObj.put("news_list", newsList);
        return rtnObj;
    }

}
