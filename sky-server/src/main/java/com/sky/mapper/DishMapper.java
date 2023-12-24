package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 用主键查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 删除
     * @param ids
     */
    void delectById(Long ids);

    /**
     * 修改菜品
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类查询id
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
