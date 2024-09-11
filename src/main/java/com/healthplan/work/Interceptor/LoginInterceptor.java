package com.healthplan.work.Interceptor;

import com.healthplan.work.vo.MemberEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN = "login";
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    // 인터셉터는 특정 경로에 대한 요청을 가로채어 추가적인 처리를 할 수 있는 기능
    // 프리핸들(PreHandle)이 먼저 실행되는 것은 스프링 프레임워크의 기본 동작 방식
    // 얘는 쿠키 관리


    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
// jsessionid > 
        HttpSession session = request.getSession();

        ModelMap modelMap = modelAndView.getModelMap();
        MemberEntity vo= (MemberEntity)modelMap.get("MemberEntity"); // 그리고 MemberEntity 타입으로 바꿔서 modelMap을 형변환해줌

        if (vo != null) {

            logger.info("new login success 로그인 성공했다!!!!!!!!!!!!");

            session.setAttribute(LOGIN, vo); // model.addAttribute("userVO", vo); // uid, upw, uname
            session.setAttribute("loginUuid", vo.getUuid()); // mno를 세션에 저장하는 쿼리 추가

            logger.info("로그엔 세션 셋 어트리뷰트한다!!!!!!!!!!!!!!!!!!!!" + vo);
            // 대체 쿠키가 어디 ㅠㅠ
            // login.jsp > input type=checkbox
            // checkbox를 선택하면 "yes"라는 값을 가져오고, 선택 안하면 null임
            if (request.getParameter("useCookie") != null) {

                logger.info("remember me................");
                // 로그인 쿠키를 만들어서 세션에서 얻어온 id 값을 "loginCookie"라는 키 값에 저장한다
                Cookie loginCookie = new Cookie("loginCookie", session.getId());
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60 * 60 * 24 * 7); // 60*60 *24=1일, 1일*7 =7일
                response.addCookie(loginCookie); // 응답에 쿠키 추가
            }
            // response.sendRedirect("/");
            Object dest = session.getAttribute("dest");

            //response.sendRedirect(dest != null ? (String) dest : "/");
            // url을 따로 치고 왔을 때는 dest로 이동해라, 아니면 기본값으로 이동!
            response.sendRedirect(dest != null ? (String) dest : "/community/listAll2");

        } else {
            logger.info("로그인 실패");
            response.sendRedirect("/member/loginError");
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute(LOGIN) != null) { // "login"
            logger.info("clear login data before 전 데이터 다 지운다!!!");
            // 로그인 값을 지움
            session.removeAttribute(LOGIN);
        }

        return true;
    }
}