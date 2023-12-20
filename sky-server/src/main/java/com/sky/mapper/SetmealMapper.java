package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 */
@Mapper
public interface SetmealMapper {

    /**
     * 查看是否关联套餐
     * @param countByCategoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{countByCategoryId}")
    Integer countByCategoryId(Long countByCategoryId);
}
