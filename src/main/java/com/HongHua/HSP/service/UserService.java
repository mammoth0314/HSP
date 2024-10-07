package com.HongHua.HSP.service;

import com.HongHua.HSP.mapper.UserMapper;
import com.HongHua.HSP.model.ApiResponse;
import com.HongHua.HSP.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public ApiResponse register(User user) {
        if (userMapper.findUserByEmail(user.getEmail()) == null) {
            userMapper.registerUser(user);
            return new ApiResponse(201, "注册成功", null);
        }
        return new ApiResponse(400, "用户已存在", null);
    }

    public ApiResponse login(String email, String password) {
        User user = userMapper.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return new ApiResponse(200, "登陆成功", "jwtData");
        }
        return new ApiResponse(401, "邮箱或密码错误", null);
    }
}
