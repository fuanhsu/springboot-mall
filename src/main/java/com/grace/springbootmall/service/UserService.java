package com.grace.springbootmall.service;

import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;

public interface UserService {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
