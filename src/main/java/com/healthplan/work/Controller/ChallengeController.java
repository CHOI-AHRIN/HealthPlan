//import java.sql.Date;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.List;
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import com.healthplan.domain.ChallengeVO;
//import com.healthplan.domain.BReplyVO;
//import com.healthplan.service.ChallengeService;
//import com.healthplan.service.BReplyService;
//
//@Controller
//@RequestMapping(value = "/challenge")
//public class ChallengeController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);
//
//	@Inject
//	private ChallengeService challengeService;
//
//	@Inject
//	private BReplyService replyService;
//
//	@RequestMapping(value = "/challengelist1", method = RequestMethod.GET)
//	public String challengeList(Model model) throws Exception {
//		logger.info("// /challenge/list");
//
//		// 최근 3개의 챌린지만 가져오기
//		List<ChallengeVO> list = challengeService.selectRecentChallenges(3);
//		logger.info("// list.toString()=" + list.toString());
//		model.addAttribute("list", list);
//
//		// 전체 댓글 목록 가져오기
//		List<BReplyVO> comments = replyService.listReplies();  // 전체 댓글 가져오기
//		model.addAttribute("comment", comments);
//		logger.info("// /comments"+comments);
//		return "challenge1/challengelist1";
//	}
//
//	@RequestMapping(value = "/challengeReg", method = RequestMethod.GET)
//	public String showChallengeReg() {
//		return "challenge1/challengeReg";
//	}
//
//	@RequestMapping(value = "/challengeReg", method = RequestMethod.POST)
//	public String submitChallenges(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
//		logger.info("submitChallenges called");
//
//		String[] contents = request.getParameterValues("contents");
//		String[] difs = request.getParameterValues("dif");
//		String[] chno = request.getParameterValues("chno");
//
//		if (contents == null || difs == null) {
//			logger.error("Contents or difs are null");
//			return "redirect:/challenge1/challengeReg"; // 에러 시 등록 페이지로 다시 리다이렉트
//		}
//
//		logger.info("/*** Contents: " + Arrays.toString(contents));
//		logger.info("/*** Difs: " + Arrays.toString(difs));
//		logger.info("/*** Chno: " + Arrays.toString(chno));
//
//		for (int i = 0; i < contents.length; i++) {
//			ChallengeVO ch = new ChallengeVO();
//			ch.setBcontents(contents[i]);
//			ch.setDif(difs[i]);
//			ch.setChno(chno[i]);
//			ch.setBtype("H"); // 게시판 타입을 'H'(챌린지 게시판)으로 설정
//			ch.setWdate(new Date(Calendar.getInstance().getTimeInMillis())); // 현재 날짜를 wdate에 설정
//
//			logger.info("Saving challenge: " + ch.toString());
//
//			challengeService.createChallengeReg(ch); // 챌린지를 DB에 저장
//		}
//
//		rttr.addFlashAttribute("msg", "SUCCESS"); // 메시지 (성공) 설정
//		return "redirect:/challenge1/challengetodayAll"; // 챌린지 목록 페이지로 리다이렉트
//	}
//
//	@RequestMapping(value = "/challengetodayAll", method = RequestMethod.GET)
//	public String challengetodayAll(ChallengeVO ch, Model model) throws Exception {
//		logger.info("show all list......................");
//		model.addAttribute("list", challengeService.selectChallengeList(ch));
//		return "challenge1/challengetodayAll";
//	}
//
//	/*
//	 * @RequestMapping(value = "/challengelistAll", method = RequestMethod.GET)
//	 * public String challengelistAll(ChallengeVO ch, Model model) {
//	 * model.addAttribute("list", ch); return "challenge1/challengelistAll"; }
//	 */
//
//	@RequestMapping(value = "/challengelistAll", method = RequestMethod.GET)
//	public String challengelistAll(Model model) {
//		try {
//			List<ChallengeVO> challengeList = challengeService.selectChallengeList();
//			model.addAttribute("list", challengeList);
//		} catch (Exception e) {
//			// 예외 로그 출력
//			logger.error("Error fetching challenge list", e);
//			// 에러 페이지로 리다이렉트
//			return "error";
//		}
//		return "challenge1/challengelistAll";
//	}
//
//}
