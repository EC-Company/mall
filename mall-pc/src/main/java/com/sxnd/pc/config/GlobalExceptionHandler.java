package com.sxnd.pc.config;

import com.sxnd.mall.exception.LoginException;
import com.sxnd.mall.result.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultInfo exceptionHandler(Exception e) {
        if (!(e instanceof LoginException)) {
            //打印一下异常堆栈信息
            e.printStackTrace();
        }
        return new ResultInfo(1, e.getMessage());
    }

}
