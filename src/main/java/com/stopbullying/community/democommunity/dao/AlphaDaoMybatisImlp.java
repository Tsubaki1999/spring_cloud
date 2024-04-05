package com.stopbullying.community.democommunity.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("AlphaMybatis") //重命名

public class AlphaDaoMybatisImlp implements AlphaDao{
    @Override
    public String select() {
        return "Mybatis";
    }
}
