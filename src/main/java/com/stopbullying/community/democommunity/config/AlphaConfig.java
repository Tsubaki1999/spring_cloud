package com.stopbullying.community.democommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

@Configuration   //普通配置类注释
public class AlphaConfig {
    @Bean  //引入第三方bean,这段方法返回的对象将被装入bean中
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    }
}
