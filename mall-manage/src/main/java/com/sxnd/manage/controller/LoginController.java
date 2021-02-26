package com.sxnd.manage.controller;

import com.sxnd.mall.result.ResultInfo;
import com.sxnd.manage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    public ResultInfo login(String username, String password, HttpSession session) {

        //登录
        boolean b = loginService.login(username, password);

        session.setAttribute("userInfo", username);

        return new ResultInfo();
    }


}
