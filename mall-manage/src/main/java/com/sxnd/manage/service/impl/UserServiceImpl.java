package com.sxnd.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.User;
import com.sxnd.mall.vo.UserVO;
import com.sxnd.manage.mapper.UserMapper;
import com.sxnd.manage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<User> queryList(UserVO userVO) {
        //设置分页参数
        PageHelper.startPage(userVO.getPage(), userVO.getLimit());
        return new PageInfo<>(userMapper.queryList(userVO));
    }

    @Override
    public void updateUserStatus(int id, int status) {
        userMapper.updateUserStatus(id, status);
    }
}
