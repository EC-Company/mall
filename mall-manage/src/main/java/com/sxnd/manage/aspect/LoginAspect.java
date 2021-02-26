package com.sxnd.manage.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoginAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Pointcut("execution(* com.sxnd.manage.service.impl.LoginServiceImpl.login(..))")
    public void login() {
    }

    @AfterReturning(pointcut = "login()", returning = "o")
    public void afterReturn(JoinPoint joinPoint, boolean o) {
        //获取参数列表
        Object[] args = joinPoint.getArgs();
        logger.info("登录用户{}-{}", args[0], args[1]);
    }


}
