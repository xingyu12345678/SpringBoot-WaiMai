package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 根据id查询分类是否还存在菜品
 */
@Mapper
public interface DishMapper {

    /**
     * 查看菜品
     * @param countByCategoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{countByCategoryId}")
    Integer countByCategoryId(Long countByCategoryId);
}
