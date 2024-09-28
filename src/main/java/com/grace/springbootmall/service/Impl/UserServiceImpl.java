package com.grace.springbootmall.service.Impl;

import com.grace.springbootmall.dao.UserDao;
import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;
import com.grace.springbootmall.service.UserService;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest);
        if (user != null) {
            log.warning("email 帳號已存在");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
