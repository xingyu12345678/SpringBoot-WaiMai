package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品查询对应的套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 新增
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where id = #{id}")
    List<SetmealDish> getById(Long id);

    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{id}")
    List<Dish> getByStamealId(Long id);

    @Delete("delete from setmeal_dish where id = #{id}")
    void delectById(Long id);
}
