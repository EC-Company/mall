package com.sxnd.manage.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor implements HandlerInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    //执行拦截之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        //获取用户登录状态
        Object o = request.getSession().getAttribute("userInfo");
        if (o != null) {
            //放行
//            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("访问：{}，放行", path);
            //不往后执行
            return true;
        }
        //重定向到登录页
        response.sendRedirect(request.getContextPath() + "/login.html");
        logger.info("访问：{}，重定向到登录页", path);
        return false;
    }

}
