package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.OrderRequestParams;
import com.grace.springbootmall.dto.UserLoginRequest;
import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.Order;
import com.grace.springbootmall.model.User;

import java.util.List;

public interface UserService {
    Integer  register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
