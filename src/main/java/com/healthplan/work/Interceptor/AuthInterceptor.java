package com.healthplan.work.Interceptor;

import com.healthplan.work.service.MemberService;
import com.healthplan.work.vo.MemberEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import jakarta.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private MemberService service;


// 	AuthInterceptor는    특정    경로에    접근하는    경우    현재   사용자가    로그인한    상태의    사용자인지를
//	체크하는    역할을    처리하기    위해서    작성한다.
    // 얘가 자동  로그인

    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {

            logger.info("current Member is not logined 로그인 안e됨@!!!!!");

            saveDest(request); // session("dest" : sboard/list")

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {

                MemberEntity MemberEntity = service.checkLoginBefore(loginCookie.getValue()); // 세션이 만료되지 않은 자의 정보

                logger.info("MemberEntity: " + MemberEntity);

                if (MemberEntity != null) {
                    session.setAttribute("login", MemberEntity); // 세션이 만료되지 않은 자의 정보
                    return true; // list
                }

            }

            response.sendRedirect("/member/login");
            return false;  // sboard/list로 못가게 막는 역할
        }
        return true;
    }


    private void saveDest(HttpServletRequest req) {

        String uri = req.getRequestURI(); // "/sboard/list"

        String query = req.getQueryString(); // null (안넣음)

        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }

        if (req.getMethod().equals("GET")) {
            logger.info("dest: " + (uri + query));
            req.getSession().setAttribute("dest", uri + query); // 갈려는 url, 쿼리가 있으면 쿼리를 출력하라~ = "/sboard/list"
        }
    }


}