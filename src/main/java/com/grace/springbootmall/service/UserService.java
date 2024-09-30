package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.UserLoginRequest;
import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;

public interface UserService {
    Integer  register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);


}
