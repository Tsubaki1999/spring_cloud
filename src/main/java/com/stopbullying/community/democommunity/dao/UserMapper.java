package com.stopbullying.community.democommunity.dao;

import com.stopbullying.community.democommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //连接bean的注释
public interface UserMapper {  //通过接口设置方法
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user);  //插入用户
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);
}

