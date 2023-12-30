package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

     void insert(User user) ;

    @Select("select * from user where openid =#{openid}")
    User getByOpenid(String openid);

    @Select("select * from user where id = #{Id}")
    User getById(Long userId);


    /**
     * 统计用户数据
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);

}
