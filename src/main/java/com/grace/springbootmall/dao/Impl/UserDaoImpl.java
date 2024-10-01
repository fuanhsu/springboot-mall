package com.grace.springbootmall.dao.Impl;

import com.grace.springbootmall.dao.UserDao;
import com.grace.springbootmall.dto.UserLoginRequest;
import com.grace.springbootmall.dto.UserRegisterRequest;
import com.grace.springbootmall.model.User;
import com.grace.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO user (email, password, created_date, last_modified_date) values(:email , :password, :createdDate,  :lastModifiedDate); ";

        Map map = new HashMap<>();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Date now = new Date();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);

        Integer userId = keyHolder.getKey().intValue();


        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id,email, password, created_date, last_modified_date FROM user WHERE user_id = :userId";
        Map map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id,email, password, created_date, last_modified_date FROM user WHERE email = :email";
        Map map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        return null;
    }
}
