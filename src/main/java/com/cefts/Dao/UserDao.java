package com.cefts.Dao;

import com.cefts.Bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public User selUserByName(String userName){
        String sql = "select * from user where user_name = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(sql,new Object[]{userName},rowMapper);
        return user;
    }
}
