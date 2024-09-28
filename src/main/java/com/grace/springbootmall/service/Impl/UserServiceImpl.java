package com.grace.springbootmall.service.Impl;

import com.grace.springbootmall.dao.UserDao;
import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;
import com.grace.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

}
