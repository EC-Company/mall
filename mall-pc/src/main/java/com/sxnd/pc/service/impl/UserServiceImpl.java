package com.sxnd.pc.service.impl;

import com.sxnd.mall.dto.WaterDTO;
import com.sxnd.mall.exception.LoginException;
import com.sxnd.mall.exception.RegisterException;
import com.sxnd.mall.exception.UserException;
import com.sxnd.mall.pojo.User;
import com.sxnd.pc.mapper.UserMapper;
import com.sxnd.pc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        //根据用户名查询用户信息
        User userInfo = userMapper.queryUserInfoByName(user.getUsername());
        //验证用户是否存在
        if (null == userInfo) {
            throw new LoginException("用户名不存在");
        }
        //验证用户密码
        if (!userInfo.getPassword().equals(user.getPassword())) {
            throw new LoginException("用户密码错误");
        }
        return userInfo;
    }

    @Override
    @Transactional
    public void register(User user) {
        //检查用户名是否已存在
        User userInfo = userMapper.queryUserInfoByName(user.getUsername());
        if (userInfo != null) {
            throw new RegisterException("用户名已存在");
        }
        //检查邮箱是否已被使用
        int count = userMapper.queryUserEmailExit(user.getEmail());
        if (count > 0) {
            throw new RegisterException("用户邮箱已被使用");
        }
        //添加用户,并且新用户需要赠送500积分
        userMapper.insertUser(user);
        //新用户赠送500积分
        this.updateUserBalance(user.getId(), 500, 0);

        //需要判断是否有邀请人
        if (user.getUserId() != null && user.getUserId() > 0) {
            //邀请人增加200积分
            this.updateUserBalance(user.getUserId(), 200, 1);
        }
    }


    /**
     * 修改积分余额
     *
     * @param userId
     * @param score
     * @return
     */
    @Override
    @Transactional
    public int updateUserBalance(int userId, int score, int type) {
        //通过userId查询用户信息
        User user = userMapper.queryUserById(userId);

        WaterDTO waterDTO = new WaterDTO();
        waterDTO.setUserId(user.getId());
        waterDTO.setNumber(score);
        waterDTO.setType(type);
        waterDTO.setStatus(type < 0 ? -1 : 1);
        userMapper.insertWater(waterDTO);
        //判断积分变化的类型
        if (waterDTO.getStatus() > 0) {
            user.setBalance(user.getBalance() + score);
        } else {
            if (user.getBalance() < score) {
                throw new UserException("用户积分余额不足");
            }
            user.setBalance(user.getBalance() - score);
        }
        return userMapper.updateUserBalance(user);
    }


}