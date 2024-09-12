package com.healthplan.work.Controller;

import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.service.MemberService;
import com.healthplan.work.util.JwtUtils;
import com.healthplan.work.vo.MemberEntity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Controller
@RequestMapping("/member")
public class MemberController {



    @Autowired
    MemberService service;

    @Autowired
    MemberMapper mapper;


    private JwtUtils jwtUtils = new JwtUtils();
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 회원리스트
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MemberEntity> selectList(Model model) throws Exception {
        logger.info("//****************************** /member/list");

        List<MemberEntity> list = mapper.selectMemberList();

        logger.info("// list.toString()=" + list.toString());

        return list;
    }

    // mNo로 회원정보 조회
/*    @RequestMapping(value = "/readMno", method = RequestMethod.POST)
    public MemberEntity selectMNo(@RequestBody MemberEntity mem) throws Exception {
        logger.info("read post ...........");
        logger.info(mem.toString());

        return mapper.selectMno(mem.getMno());
    }*/

    /// 회원가입
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String insertMemPOST(@RequestBody MemberEntity mem) throws Exception {

        logger.info("/*********************** 회원가입!! regist post ...........");
        logger.info(mem.toString());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(mem.getUpw());

        System.out.println("해시된 비밀번호:" + hashedPassword);

        mem.setUpw(hashedPassword);

        mapper.insertMem(mem);
        mapper.currval(); // 현재 회원번호 조회
        mapper.setpoint(mem.getMno()); // 기본 포인트 등록!

        return "success";
    }

    // 로그인
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public ResponseEntity<?> loginPOST(@RequestBody LoginDTO dto) throws Exception {

        logger.info("// /loginPost");
        logger.info(dto.toString());
        final String token = jwtUtils.generateToken(dto.getUuid());
        System.out.println("/*** encordingStr=" + token);

        String decordedStr = jwtUtils.getUuidFromToken(token);
        System.out.println("/*** decordingStr=" + decordedStr);

        boolean memId = jwtUtils.validateToken(token, dto.getUuid());
        System.out.println("email=" + memId);

        String storedHashedPassword = mapper.getHashedPasswordByUuid(dto.getUuid());

        logger.info("Stored Hashed Password: " + storedHashedPassword);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(dto.getUpw(), storedHashedPassword);

        logger.info("비밀번호 일치 여부: " + isPasswordMatch);

        if (isPasswordMatch) {
            dto.setUpw(storedHashedPassword);
            logger.info("/*** dto.toString()=" + dto.toString());
            MemberEntity loggedInMember = mapper.login(dto);

            // 토큰을 응답 헤더에 포함시켜 클라이언트로 전송
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + token);

            return new ResponseEntity<>(loggedInMember, responseHeaders, HttpStatus.OK);
        } else { // 불일치 시 오류 응답 반환
            return new ResponseEntity<>("로그인 실패 메시지", HttpStatus.UNAUTHORIZED);
        }
    }

    // 쿠키 관리
    @RequestMapping(value = "/loginCookie", method = RequestMethod.POST)
    public MemberEntity loginCookie(@RequestBody LoginDTO dto) throws Exception {

        logger.info("/*************** /loginCookie 시작...");
        logger.info("/*************** dto 뭐 받았니"+dto.toString());
        final String token = jwtUtils.generateToken(dto.getUuid());
        System.out.println("/*************** encordingStr=" + token);

        String decordedStr = jwtUtils.getUuidFromToken(token);
        System.out.println("/********************* decordingStr=" + decordedStr);

        boolean uuid = jwtUtils.validateToken(token, dto.getUuid());
        System.out.println("/********************* uuid=" + uuid);

        String storedHashedPassword = mapper.getHashedPasswordByUuid(dto.getUuid());

        logger.info("Stored Hashed Password: " + storedHashedPassword);

        boolean isPasswordMatch = false;
        if (storedHashedPassword != null && dto.getUpw() != null) {
            isPasswordMatch = storedHashedPassword.equals(dto.getUpw());
        } else {
            return null;
        }

        logger.info("비밀번호 일치 여부: " + isPasswordMatch);

        if (isPasswordMatch) {
            // dto.setPw(storedHashedPassword);
            logger.info("/*** dto.toString()=" + dto.toString());
            return mapper.login(dto);
        } else {
            return null;
        }
    }

    // 로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        Object obj = session.getAttribute("login");

        if (obj != null) {
            MemberEntity mem = (MemberEntity) obj;

            session.removeAttribute("login");
            session.invalidate();

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                mapper.keepLogin(mem.getUuid(), session.getId(), new Date());
            }
        }
        return "success";
    }

    //아이디 중복체크
    @RequestMapping(value = "/uuidCk", method = RequestMethod.POST)
    public MemberEntity eamilCk(@RequestBody MemberEntity mem) throws Exception {
        logger.info("/******************** uuidCk post ...........");
        logger.info(mem.toString());

        return mapper.uuidCk(mem.getUuid());
    }

/*
    // 이메일로 mid 체크
    @RequestMapping(value = "/midCk", method = RequestMethod.POST)
    public MemberEntity midCk(@RequestBody MemberEntity mem) throws Exception {
        logger.info("midCk post ...........");
        logger.info(mem.toString());

        return mapper.midCk(mem.getMemId());

    }
*/

/*
    // 닉네임 중복체크
    @RequestMapping(value = "/nameCk", method = RequestMethod.POST)
    public MemberEntity ninameCk(@RequestBody MemberEntity mem) throws Exception {
        logger.info("ninameCk post ...........");
        logger.info(mem.toString());

        return mapper.ninameCk(mem.getName());
    }
*/

    // 마이페이지 회원정보 조회
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public MemberEntity selectMemId(@RequestBody MemberEntity mem) throws Exception {

        // model.addAttribute("mem", membermapper.readMember(email));
        logger.info("조회할 이메일 : " + mem.getUuid());

        return mapper.selectUuid(mem.getUuid());
    }

    // 마이페이지 회원정보 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String updatePOST(@RequestBody MemberEntity mem, RedirectAttributes rttr) throws Exception {

        logger.info(mem.toString());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(mem.getUpw());

        System.out.println("해시된 비밀번호:" + hashedPassword);

        mem.setUpw(hashedPassword);

        mapper.update(mem);

        rttr.addAttribute("name", mem.getName());
        rttr.addFlashAttribute("msg", "SUCCESS");

        logger.info(rttr.toString());

        return "SUCCESS";

    }

    // 회원탈퇴
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String delete(@RequestBody MemberEntity mem) throws Exception {

        logger.info("delete post ...........");
        logger.info(mem.toString());

       mapper.delete(mem.getUuid());

        return "succ";
    }



/*

    // 9/11 String 타입에서 void로 타입 변환
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
        logger.info("로그인 컨트롤러에 로그인 겟 돌았다", dto);
        // return "member/login"; // 뷰 이름 반환
    }

    // dto: 데이터 전송 객체 > uuid, upw, useCookie 밖에 안듦;
    // 요청 -> preHandle() -> 컨트롤러 -> postHandle() -> 뷰 렌더링 -> afterCompletion()
    // 9/11 String 타입에서 Map으로 타입 변환
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public Map<String, Object> loginPOST(LoginDTO dto, HttpSession session, Model model, RedirectAttributes rttr) throws Exception {


        logger.info("로그인 컨트롤러에 로그인 포스트 돌았다", dto);

        Map<String, Object> loginpostObj = new HashMap<>();

        // 사용자가 넘긴 값을 담음(dto), 그리고 서비스로 이동
        // 9/11 주석처리 > MemberEntity vo = service.login(dto); // 여긴 모든 내용이 다 들었음
        List<MemberEntity> memEnt =  service.login(dto);

        logger.info("로그인 포스트안에 뭐들었어 !!!" + dto);
        logger.info("로그인 포스트안에 담은 vo 뭐들었어 !!!" + memEnt); // 9/11 vo를 memEnt로 변경

        if (memEnt == null) { // 9/11 vo를 memEnt로 변경
            // 9/11 model 주석처리 >  model.addAttribute("errorMessage", "로그인 정보가 맞지 않습니다.");
            rttr.addFlashAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            //  return "redirect:/member/loginError";
            // 9/11 주석처리 > return "member/login"; // 로그인 정보가 맞지 않으면 에러 페이지로 리다이렉트
        }

        // login한 사람의 모든 정보
        loginpostObj.put("memEnt", memEnt);
        // 9/11 주석처리 >  model.addAttribute("MemberEntity", vo);

        // 세션에 로그인한 사용자의 uuid 저장
        session.setAttribute("loginMno", memEnt.getMno()); // // 9/11 vo를 memEnt로 변경
        // 리스트에서 첫 번째 MemberEntity 객체를 추출
        if (!memEnt.isEmpty()) {
            MemberEntity member = memEnt.get(0);
            session.setAttribute("loginMno", member.getMno());
            session.setAttribute("loginUuid", member.getUuid());
        } else {
            // 로그인 실패 처리
        }

        // 쿠키 사용
        // view에서 remember me 체크하면 ture, 아니면 false
        if (dto.isUseCookie()) { // 쿠키반환함

            int amount = 60 * 60 * 24 * 7; // 7일

            // 세션 유지 제한 시간
            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));

            // 세션을 유지하도록 요청하는 기능을 수행
            service.keepLogin(memEnt.getUuid(), session.getId(), sessionLimit); // 9/11 vo를 memEnt로 변경

            logger.info("킵로그인  어쩌구~ " + memEnt.getUuid() + session.getId(), sessionLimit); // 9/11 vo를 memEnt로 변경

        }
        // 9/11 리턴문 주석 > return "/community/listAll2";
        return loginpostObj;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        Object obj = session.getAttribute("login");

        if (obj != null) {
            MemberEntity vo = (MemberEntity) obj;

            session.removeAttribute("login");
            session.invalidate();

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                service.keepLogin(vo.getUuid(), session.getId(), new Date());
            }
        }

        return "member/logout";
    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError() {
        return "member/loginError"; // 로그인 에러 페이지 뷰 반환
    }

    // 회원가입
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public void joinGET() throws Exception {

        logger.info("join get !!...........");
    }

    // 회원가입 정보 전달~
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinPOST(MemberEntity vo, RedirectAttributes rttr) throws Exception {
        int result = service.uuidcheck(vo);
        logger.info("join post!!!!!!!!!!!!!!!!!!!");
        try {
            if(result == 1) {
                return "/member/join";
            } else if (result == 0) {
                service.join(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/member/loginError"; // 오류 페이지로 이동
        }

        logger.info(" 컨트롤러 조인 ~! join에 뭐보내니         " + vo.toString());
        return "/member/login" ;
        // logger.info("join에 뭐보내니" + member);
        // return "/";
    }

    // 아이디 중복 체크
    @ResponseBody
    @RequestMapping(value="uuidcheck", method=RequestMethod.POST)
    public int uuidcheck(MemberEntity vo) throws Exception{
        int result = service.uuidcheck(vo);
        return result;
    }

    // 회원정보 페이지
    // 원래도 이렇게 썼으나 .. url에 uuid=하고 값은 표현되지 않더니 갑자기 표현 없이도 그 주소로 넘어가 가능해짐... sevlet에
    // 설정을 해서 그런가?ㅠ
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String loginProfile(@ModelAttribute("uuid") String uuid, HttpSession session, Model model) throws Exception {

        try { // 세션에서 로그인한 사용자의 uuid를 가져옴
            uuid = (String) session.getAttribute("loginUuid");

            // 세션에 로그인 정보가 없을 경우 접근 차단
            if (uuid == null) {
                logger.error("잘못된 접근 시도: 세션에 로그인 정보가 없습니다.");
                // 로그인 페이지로 리다이렉트
                return "redirect:/member/login";
            }

            // 서비스 호출 및 모델에 데이터 추가
            MemberEntity MemberEntity = service.profile(uuid);
            // model.addAttribute("MemberEntity", MemberEntity);

            // JSP 페이지로 이동
            return "member/profile";

        } catch (Exception e) {
            logger.error("오류 발생: ", e);
            throw e;
        }
    }

    // 회원정보수정
    // requestparam은 HTTP 요청의 쿼리 매개변수, 폼 데이터, 또는 URL 경로에서 파라미터를 추출하는 데 사용
    //  클라이언트가 보내는 HTTP 요청의 데이터를 메서드의 매개변수로 바인딩할 수 있음
    @RequestMapping(value = "/profileModify", method = RequestMethod.POST)
    public String loginProfileModify(@RequestParam String upw, @RequestParam String phone, @RequestParam String email,
                                     @RequestParam String uuid, HttpSession session, RedirectAttributes rttr) throws Exception {

        try {
            logger.info("회원정보 수정 시작한다~!~!!");
            // 세션에서 로그인한 uuid 값 받아오기
            uuid = (String) session.getAttribute("loginUuid");

            if (upw == null || upw.trim().isEmpty()) {
                rttr.addFlashAttribute("errorMsg", "비밀번호를 꼭 입력해주세요.");
                logger.info("여기서 리턴 되느냐 안되느냐 그게 문제다 ..");
                return "redirect:/member/profile";
            }

            // 입력값 로그로 출력하여 확인
            System.out.println("Password: " + upw);
            System.out.println("Phone: " + phone);
            System.out.println("Email: " + email);
            System.out.println("UUID: " + uuid);

            // 데이터 유효성 검사
            if (upw == null || phone == null || email == null || uuid == null) {
                throw new IllegalArgumentException("모든 파일은 보호받고 있다!!!");
            }

            service.profileModify(upw, phone, email, uuid);

            // 플래시 메시지
            rttr.addFlashAttribute("msg", "SUCCESS");

        } catch (Exception e) {
            rttr.addFlashAttribute("msg", "SUCCESS");
            e.printStackTrace();
        }

        logger.info("회원정보 수정 끝~!~!!" + uuid);
        ResponseEntity.ok("Member updated successfully");

        return "redirect:/member/logout";
    }

    // 회원 탈퇴 페이지
    @RequestMapping(value="/deletePage", method=RequestMethod.GET)
    public String deletePage(
            */
/* @ModelAttribute("uuid") String uuid, *//*
 HttpSession session, RedirectAttributes rttr, Model model) throws Exception{

        String uuid = (String) session.getAttribute("loginUuid");
        if (uuid == null) {
            return "redirect:/member/login";
        }
        logger.info("/********************************** 딜리트 페이지 uuid 읽어오니"+uuid);
        MemberEntity MemberEntity = service.profile(uuid);
        // model.addAttribute("MemberEntity", MemberEntity);
        logger.info("=====================================================> 그래서 탈퇴하려는 회원 정보 뭐야    " + MemberEntity);


        // 리다이렉트는 클라이언트(브라우저)에게 새로운 URL로 이동하도록 요청하는 것(브라우저 주소 변경, 새로운 요청)
        // 새로운 요청: 클라이언트가 새로운 요청을 보내므로 세션, 쿠키, 파라미터 등이 초기화 됨
        // 두 개의 HTTP 요청이 발생, URL 파라미터나 세션을 사용하지 않으면 데이터를 전달하기 어려움

        // 뷰 반환은 서버가 클라이언트에게 HTML 콘텐츠를 반환하는 것,  클라이언트는 새로운 HTTP 요청을 보내지 않음
        // 서버가 클라이언트에게 HTML 콘텐츠를 반환하는 것, 클라이언트는 새로운 HTTP 요청을 보내지 않음
        // 단일 요청: 하나의 HTTP 요청과 응답으로 처리가 완료, 데이터 전달 용이: 모델 객체에 데이터를 담아 뷰에 전달하기 쉬움
        // 브라우저 주소 유지: 브라우저 주소 표시줄의 URL이 변경되지 않음, 중복 요청 위험: POST 요청 후에 새로고침을 하면 중복 요청이 발생할 수 있음

        // 리다이렉트가 아닌 그냥 뷰로 반환하니 뷰가 불러와짐
        return "member/deletePage";
    }

    // 회원탈퇴
    @RequestMapping(value="/deletePage", method=RequestMethod.POST)
    public String delete (@RequestParam("uuid") String uuid, HttpSession session, RedirectAttributes rttr) throws Exception{

        logger.info("MemberController 딜리트페이지 포스트 돌았다 ;; ============================== >  "+uuid);
        String chkuuid = (String) session.getAttribute("loginUuid");

        if (!uuid.equals(chkuuid)) {
            return "redirect:/login"; // uuid가 일치하지 않으면 로그인 페이지로 리다이렉트
        }



        service.delete(uuid);
        logger.info("/******************************삭제 확인 ~~~ (MemCon)    "+uuid);
        //session.invalidate(); // 세션 무효화 (로그아웃)
        return "redirect:/member/logout";
    }


    // 회원 탈퇴 시 비밀번호 체크
    @ResponseBody
    @RequestMapping(value="/upwcheck", method = RequestMethod.POST)
    public int upwcheck(@RequestParam("upw") String upw, HttpSession session)throws Exception{
        String uuid =(String) session.getAttribute("loginUuid");
        MemberEntity vo = new MemberEntity();

        vo.setUuid(uuid);
        vo.setUpw(upw);

        int result = service.upwcheck(vo);

        return result;
    }
*/


}
