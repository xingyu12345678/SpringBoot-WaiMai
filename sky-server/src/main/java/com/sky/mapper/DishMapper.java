package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
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


    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分类查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> page(DishPageQueryDTO dishPageQueryDTO);
}
