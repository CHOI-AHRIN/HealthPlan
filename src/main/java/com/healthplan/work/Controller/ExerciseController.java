package com.healthplan.work.Controller;

import com.healthplan.work.service.ExerciseService;
import com.healthplan.work.vo.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healthplan.work.vo.ExerciseEntity;
//import com.healthplan.service.DietService;
//import com.healthplan.work.service.ExerciseService;

@Controller
@RequestMapping(value = "/community/*")
public class ExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	@Autowired
	private ExerciseService service;
	
	// 지원
	 @RequestMapping(value = "/elist", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info(cri.toString());
	    logger.info("/****************** 운동 페이지 메이커에 셋크리~" + cri.toString());
	    // model.addAttribute("list", service.listCriteria(cri));
	    model.addAttribute("elist", service.listSearchCriteria(cri));

	   /* PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
		logger.info("/*******************운동 페이지 메이커에 셋크리~" + cri.toString());
	    // pageMaker.setTotalCount(service.listCountCriteria(cri));
	    pageMaker.setTotalCount(service.listSearchCount(cri));
	    logger.info("/*******************운동 페이지 메이커에 셋토탈카운트~" + cri.toString());
	    model.addAttribute("pageMaker", pageMaker);*/
	  }

	  @RequestMapping(value = "/ereadPage", method = RequestMethod.GET)
	  public void read(@RequestParam("cno") Integer cno, @ModelAttribute("cri") SearchCriteria cri, Model model)
	      throws Exception {
		  //logger.info(cri.toString());
		  // (아린)앞에 모델 이름 수정
		  model.addAttribute("ExerciseEntity", service.read(cno));
	  }


	  @RequestMapping(value = "/eremovePage", method = RequestMethod.POST)
	  public String remove(@RequestParam("cno") Integer cno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

	    service.remove(cno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/community/elist";
	  }

	  @RequestMapping(value = "/emodifyPage", method = RequestMethod.GET)
	  public void modifyGET(Integer cno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    model.addAttribute(service.read(cno));
	  }

	  @RequestMapping(value = "/emodifyPage", method = RequestMethod.POST)
	  public String modifyPOST(ExerciseEntity community, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

	    logger.info(cri.toString());
	    service.modify(community);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    logger.info(rttr.toString());

	    return "redirect:/community/elist";
	  }
	  
	  @RequestMapping(value = "/eregister", method = RequestMethod.GET)
	  public void registGET() throws Exception {

	    logger.info("regist get ...........");
	  }

	  @RequestMapping(value = "/eregister", method = RequestMethod.POST)
	  public String registPOST(ExerciseEntity community, RedirectAttributes rttr) throws Exception {

	    logger.info("regist post ...........");
	    logger.info(community.toString());

	    
	    service.regist(community);

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/community/elist";
	  }
	  
	  
		/*
		 * @RequestMapping("/getAttach/{cno}")
		 * 
		 * @ResponseBody public List<String> getAttach(@PathVariable("cno") Integer
		 * cno)throws Exception{
		 * 
		 * return service.getAttach(cno); }
		 */

}
