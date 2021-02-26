package com.sxnd.manage.mapper;

import com.sxnd.mall.pojo.User;
import com.sxnd.mall.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryList(UserVO userVO);

    void updateUserStatus(@Param("id") int id, @Param("status") int status);
}
