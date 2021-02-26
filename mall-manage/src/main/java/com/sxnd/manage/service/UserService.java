package com.sxnd.manage.service;

import com.github.pagehelper.PageInfo;
import com.sxnd.mall.pojo.User;
import com.sxnd.mall.vo.UserVO;

public interface UserService {
    PageInfo<User> queryList(UserVO userVO);

    void updateUserStatus(int id, int status);
}
