package com.sxnd.manage.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    //定义不拦截路径
    private Set<String> set = new HashSet<>();

    //路径匹配器
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //设置不拦截路径
        set.add("/static/**");
        set.add("/login.html");
        set.add("/login");
        logger.info("LoginFilter init success!!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取访问路径
        String path = request.getServletPath();
        //验证路径是否需要拦截
        boolean b = ifFilter(path);
        if (!b) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("访问：{}，放行", path);
            //不往后执行
            return;
        }
        //获取用户登录状态
        Object o = request.getSession().getAttribute("userInfo");
        if (o != null) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("访问：{}，放行", path);
            //不往后执行
            return;
        } else {
            //重定向到登录页
            response.sendRedirect(request.getContextPath() + "/login.html");
            logger.info("访问：{}，重定向到登录页", path);
        }
    }

    /**
     * 判断路径释放需要拦截
     *
     * @param path
     * @return
     */
    private boolean ifFilter(String path) {
        for (String s : set) {
            boolean match = pathMatcher.match(s, path);
            if (match) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void destroy() {
        logger.info("LoginFilter destroy!!");
    }
}
