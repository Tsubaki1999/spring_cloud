package com.stopbullying.community.democommunity.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("AlphaHibernate")   //访问数据库注解
@Primary      //多个可实现时，优先装配
public class AlphaDaoHibernateImlp implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
