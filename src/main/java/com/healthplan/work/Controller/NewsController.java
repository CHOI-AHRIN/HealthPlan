package com.healthplan.work.Controller;

import com.healthplan.work.service.NewsService;
import com.healthplan.work.vo.NewsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class NewsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NewsService newsService;

	@RequestMapping("/news")
	public Map<String, Object> news() throws Exception {
		Map<String, Object> rtnObj = new HashMap<>();

		List<NewsEntity> newsList = newsService.selectList();
		logger.info("news-> " + newsList.toString());

		rtnObj.put("newslist", newsList);
		return rtnObj;
	}

	@RequestMapping("/hello")
	public String hello() throws Exception {
		return "Hello React!";
	}
}
