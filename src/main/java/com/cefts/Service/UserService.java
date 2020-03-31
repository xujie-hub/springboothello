package com.cefts.Service;

import com.cefts.Bean.User;
import com.cefts.Dao.UserDao;
import com.cefts.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserDao userDao;

    public Iterable<User> getUser(){
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public User selUserByName(String userName){
        User user = userDao.selUserByName(userName);
        return user;
    }
}
