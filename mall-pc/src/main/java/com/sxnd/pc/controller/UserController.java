package com.sxnd.pc.controller;

import com.sxnd.mall.pojo.User;
import com.sxnd.mall.result.ResultInfo;
import com.sxnd.pc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("login")
    public ResultInfo login(User user, HttpSession session) {
        //执行登录，并返回用户
        User userInfo = userService.login(user);
        //讲用户信息存放到session
        session.setAttribute("userInfo", userInfo);
        return ResultInfo.ok();
    }

    @RequestMapping("register")
    public ResultInfo register(User user) {
        //用户注册
        userService.register(user);

        return ResultInfo.ok();
    }
}
