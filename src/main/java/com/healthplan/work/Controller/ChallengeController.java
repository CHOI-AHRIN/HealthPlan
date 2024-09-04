package com.healthplan.work.Controller;

import com.healthplan.work.dao.NewsMapper;
import com.healthplan.work.vo.NewsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChallengeController {

	// hehe
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NewsMapper newsMapper;
	
	@RequestMapping("/api/news")
	@ResponseBody
	public Map<String, Object> news() throws Exception {
		Map<String, Object> rtnObj = new HashMap<>();

		List<NewsEntity> newsList = newsMapper.listNews();
		logger.info("news-> " + newsList.toString());

		rtnObj.put("news_list", newsList);
		return rtnObj;
	}

}
