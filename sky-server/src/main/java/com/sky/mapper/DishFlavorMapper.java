package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 新增口味
     * @param flavors
     */
    void insertBath(List<DishFlavor> flavors);


    /**
     * 批量删除
     * @param dishIds
     */
    @Delete("delete from dish_flavor where dish_id = #{dishIds}")
    void delectByIds(Long dishIds);

    /**
     * 根据id查询口味哦
     *
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where id = #{id}")
    List<DishFlavor> getByDishId(Long id);
}
