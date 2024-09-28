package com.grace.springbootmall.dao;

import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
