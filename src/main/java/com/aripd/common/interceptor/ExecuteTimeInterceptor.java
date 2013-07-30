package com.aripd.common.interceptor;

import com.aripd.member.domain.Member;
import com.aripd.member.domain.Memberlog;
import com.aripd.member.service.MemberService;
import com.aripd.member.service.MemberlogService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(ExecuteTimeInterceptor.class);
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberlogService memberlogService;
    @Autowired
    private MemberService memberService;

    // before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true;
    }

    // after the handler is executed
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

        // log it
        if (logger.isDebugEnabled()) {
            logger.debug("[" + handler + "] executeTime : " + executeTime + "ms");
        }

        // modified the existing modelAndView
        String type;
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            type = "ajax";
            System.out.println("Ajax Execution Time : " + executeTime + "ms");
        } else {
            type = "page";
            //modelAndView.addObject("executeTime", executeTime);
            System.out.println("Page Execution Time : " + executeTime + "ms");
        }

        /**
         * Save the log to the database
         */
        SecurityContext context = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            Member member = memberService.findOneByUsername(authentication.getName());

            Memberlog memberlog = new Memberlog();
            memberlog.setMember(member);
            memberlog.setSessionId(request.getSession().getId());
            memberlog.setIpAddress(request.getRemoteAddr());
            memberlog.setCreatedAt(new DateTime(startTime));
            memberlog.setUpdatedAt(new DateTime(endTime));
            memberlog.setExecuteTime(executeTime);
            memberlog.setUrl(request.getRequestURL().toString());
            memberlog.setType(type);
            memberlogService.save(memberlog);
        }
    }
}
