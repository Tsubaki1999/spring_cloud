package com.stopbullying.community.democommunity.dao;

import com.stopbullying.community.democommunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    //用于显示帖子列表或查询个人帖子
    //offset为设置每页起始行号，limit为每页帖子数量，userId预留给查询个人帖子

    // @Param注解用于给参数取别名,可以解决名字过长的问题
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.动态参数
    int selectDiscussPostRows(@Param("userId") int userId);
    //查询帖子行数

}
