package com.healthplan.work.Controller;

import com.healthplan.work.service.ExerciseService;
import com.healthplan.work.vo.DietEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healthplan.work.vo.ExerciseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.healthplan.service.DietService;
//import com.healthplan.work.service.ExerciseService;

@Controller
@RequestMapping( "/community/*")
public class ExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	@Autowired
	ExerciseService service;

/*	@RequestMapping(value = "/elist", method = RequestMethod.GET)
	public String elist(Model model) {
		model.addAttribute("list", service.listAll());

		return "/community/elist";
	}*/

	// 페이지 읽기 - get
	@GetMapping (value = "/readPage/{cno}")
	public @ResponseBody HashMap<String, Object> read(@RequestParam("cno") int cno) throws Exception {

		Map<String, Object> readObj = new HashMap<>();

		List<ExerciseEntity> read = service.readPage(cno);
		logger.info("/******************* 게시글 읽기 가보자고!->" + readObj.toString());

		readObj.put("read", read);
		return new HashMap<>(readObj);
	}
	


}
