package com.stopbullying.community.democommunity.service;

import com.stopbullying.community.democommunity.dao.AlphaDao;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service    //业务组件注释
//@Scope("prototype")    //每次请求都会创建一个实例
public class AlphaService {

    @Autowired  //注入依赖Dao
    private AlphaDao alphaDao;
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct   //表示初始化在构造之后登录
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy     //在销毁之前调用
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
