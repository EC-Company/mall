package com.sxnd.manage.service.impl;

import com.sxnd.mall.exception.LoginException;
import com.sxnd.manage.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Override
    public boolean login(String username, String password) {
        if (!this.username.equals(username)) {
            throw new LoginException("用户名错误");
        }
        if (!this.password.equals(password)) {
            throw new LoginException("密码错误");
        }
        return true;
    }
}
