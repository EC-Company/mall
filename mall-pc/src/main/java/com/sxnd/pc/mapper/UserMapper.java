package com.sxnd.pc.mapper;

import com.sxnd.mall.dto.WaterDTO;
import com.sxnd.mall.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //根据用户名查询用户信息
    User queryUserInfoByName(@Param("username") String username);

    //检查用户邮箱是否已被使用
    int queryUserEmailExit(@Param("email") String email);

    //添加用户
    int insertUser(User user);

    //插入流水
    int insertWater(WaterDTO waterDTO);

    //修改用户积分余额
    int updateUserBalance(User user);

    //根据用户id查询用户信息
    User queryUserById(@Param("userId") int userId);
}
