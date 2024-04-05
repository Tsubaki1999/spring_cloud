package com.stopbullying.community.democommunity.service;

import com.stopbullying.community.democommunity.dao.UserMapper;
import com.stopbullying.community.democommunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
    //根据userid查找User

}
