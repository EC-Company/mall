package com.sxnd.pc.service;


import com.sxnd.mall.dto.WaterDTO;
import com.sxnd.mall.pojo.User;

public interface UserService {
    User login(User user);

    void register(User user);

    int updateUserBalance(int userId, int score,int type);

}
