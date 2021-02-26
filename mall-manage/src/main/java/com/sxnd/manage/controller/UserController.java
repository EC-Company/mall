package com.sxnd.manage.controller;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.User;
import com.sxnd.mall.result.ResultInfo;
import com.sxnd.mall.vo.UserVO;
import com.sxnd.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("queryUserList")
    public ResultInfo queryList(UserVO userVO) {
        //查询分页列表
        PageInfo<User> pageInfo = userService.queryList(userVO);
        return new ResultInfo(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("updateUserStatus")
    public ResultInfo updateUserStatus(int id, int status) {

        //修改用户状态
        userService.updateUserStatus(id, status);

        return new ResultInfo();
    }


}
